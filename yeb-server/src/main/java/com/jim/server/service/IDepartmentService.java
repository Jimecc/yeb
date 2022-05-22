package com.jim.server.service;

import com.jim.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jim.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * @Author: Jim
     * @Description: 获取所有部门
     * @Params: 
     */
    List<Department> getAllDepartments();

    /**
     * @Author: Jim
     * @Description: 添加部门
     * @Params: 
     */
    RespBean addDep(Department department);

    /**
     * @Author: Jim
     * @Description: 删除部门
     * @Params: 
     */
    RespBean delDep(Integer id);
}
