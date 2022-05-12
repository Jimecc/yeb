package com.jim.server.controller;


import com.jim.server.pojo.Admin;
import com.jim.server.pojo.AdminLoginParam;
import com.jim.server.pojo.RespBean;
import com.jim.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags="LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService ;

    @ApiOperation(value="登陆之后返回 token")
    @PostMapping("/login")
    public RespBean login (@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value="测试接口")
    @PostMapping("/test")
    public String test (){
        return "Success";
    }

    @ApiOperation(value="退出")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("退出成功");
    }

    @ApiOperation(value="获取当前登录用户信息")
    @PostMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        admin.setPassword(null);
        return admin;
    }
}
