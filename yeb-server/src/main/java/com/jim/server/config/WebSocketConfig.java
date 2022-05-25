package com.jim.server.config;

import com.jim.server.config.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Jim
 * @Description WebSocket 配置类
 * @createTime 2022年05月26日
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @Author: Jim
     * @Description: 添加这个 EndPoint，这样可以在网页通过 websocket 连接上服务
     * 也就是我们配置 websocket 服务复制，并且可以指定是否使用 socketJS
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1.将 ws/ep路径注册为 stomp 的端点，用户链接了这个端点就可以进行 websocket 通讯，支持 socketJS
         * 2.setAllowedOrigins("*")允许跨域
         * wethSockJS() 支持 socketJS 访问
         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 输入通道参数配置
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为连接，如果是，需要获取token，并且设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)){
                        String authToken = token.substring(tokenHead.length());
                        String username = jwtTokenUtil.getUserNameFromToken(authToken);
                        //token中存在用户名
                        if (!StringUtils.isEmpty(username)){
                            //登录
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            //验证token是都有效
                            if (jwtTokenUtil.validateToken(authToken,userDetails)){
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * @Author: Jim
     * @Description: 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置代理域，可以配置多个，配置代理的目的地前缀为/queue,可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}

