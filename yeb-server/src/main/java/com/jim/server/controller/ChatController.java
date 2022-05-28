package com.jim.server.controller;

import com.jim.server.pojo.Admin;
import com.jim.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jim
 * @Description 可以跟谁聊天
 * @createTime 2022年05月26日
 */

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IAdminService adminService;


    @ApiOperation(value="获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }
}
