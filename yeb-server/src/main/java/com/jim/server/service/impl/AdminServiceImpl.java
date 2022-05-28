package com.jim.server.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jim.server.config.security.JwtTokenUtil;
import com.jim.server.mapper.AdminMapper;
import com.jim.server.mapper.AdminRoleMapper;
import com.jim.server.mapper.RoleMapper;
import com.jim.server.pojo.Admin;
import com.jim.server.pojo.AdminRole;
import com.jim.server.pojo.RespBean;
import com.jim.server.pojo.Role;
import com.jim.server.service.IAdminService;
import com.jim.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * @error
     * @Caused_By: 还没有配置 PasswordEncoder
     * @Solved:  Done
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String)request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null == userDetails || ! passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }

        // 更新 security 登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成 token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登陆成功",tokenMap);
//        return null;
    }

    /**
     * @Author: Jim
     * @Description: 根据用户名获取用户
     * @Params: 
     */
    @Override
    public Admin getAdminByUsername(String username) {
        // 使用 MyBatis-Plus 查找一个用户，要求：username=传进来的用户名，且 enable=true（未被封禁）
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    /**
     * @Author: Jim
     * @Description: 根据用户 id 查询角色列表
     * @Params:
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    @Override
    public List<Admin> getAllAdmins(String keywords) {

        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId,rids);
        if(rids.length == result) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * @Author: Jim
     * @Description: 更新用户密码
     * @param oldPass
     * @param pass
     * @param adminId
     */
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 判断旧密码是否正确
        if(encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if(1 == result){
                return  RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespBean updateAdminUserFace(String url, Integer id, Authentication authentication) {
        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result = adminMapper.updateById(admin);
        if(1 == result){
            Admin principal = (Admin)authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return RespBean.success("更新成功",url);
        }
        return RespBean.error("更新失败");
    }


}
