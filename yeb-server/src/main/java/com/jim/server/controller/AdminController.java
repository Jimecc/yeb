package com.jim.server.controller;


import com.jim.server.pojo.Admin;
import com.jim.server.pojo.RespBean;
import com.jim.server.pojo.Role;
import com.jim.server.service.IAdminService;
import com.jim.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value="更新操作员")
    @PutMapping("/")
    public RespBean uodateAdmin(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return RespBean.success("更新成功");
        }

        return RespBean.error("更新失败");
    }

    @ApiOperation(value="删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        log.info("Delete Admin");
        if(adminService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value="获取所有角色")
    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.list();
    }

    @ApiOperation(value="更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }

}
