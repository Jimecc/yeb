package com.jim.server.controller;


import com.jim.server.pojo.Department;
import com.jim.server.pojo.RespBean;
import com.jim.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value="获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value="添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department department) {
        return departmentService.addDep(department);
    }

    @ApiOperation(value="删除部门")
    @DeleteMapping("/{id}")
    public RespBean delDep(@PathVariable Integer id){
        return departmentService.delDep(id);
    }


}
