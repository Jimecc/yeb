package com.jim.server.controller;


import com.jim.server.pojo.Admin;
import com.jim.server.pojo.AdminLoginParam;
import com.jim.server.pojo.RespBean;
import com.jim.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginCOntroller")
@RestController
public class LoginController {


    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回 Token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }


    @ApiOperation(value="获取当前登录用户的信息")
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
    @ApiOperation(value="退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }


}
