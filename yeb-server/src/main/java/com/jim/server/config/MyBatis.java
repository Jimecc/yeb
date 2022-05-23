package com.jim.server.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Jim
 * @Description: MyBatis-Plus 分页插件
 * @Params:
 */

@Configuration
public class MyBatis {


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        return new MybatisPlusInterceptor();
    }


}
