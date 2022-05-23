package com.jim.server.controller;


import com.jim.server.pojo.Employee;
import com.jim.server.pojo.RespPageBean;
import com.jim.server.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {


    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "查询所有员工并分页")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee, LocalDate[] beginDateScope){
        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);

    }

}
