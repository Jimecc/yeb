package com.jim.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jim.server.pojo.Employee;
import com.jim.server.pojo.RespBean;
import com.jim.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * @Author: Jim
     * @Description: 获取所有员工（分页）
     * @Params: 
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * @Author: Jim
     * @Description: 获取最大工号
     */
    RespBean maxWorkID();

    /**
     * @Author: Jim
     * @Description: 添加员工
     * @param employee
     */
    RespBean addEmp(Employee employee);

    /**
     * @Author: Jim
     * @Description: 查询员工
     * @param id
     */
    List<Employee> getEmployee(Integer id);

    /**
     * @Author: Jim
     * @Description: 获取所有员工账套
     * @param currentPage
     * @param size
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
