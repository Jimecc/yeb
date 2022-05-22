package com.jim.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jim.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * @Author: Jim
     * @Description: getAllDepartments
     * @Params: 
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * @Author: Jim
     * @Description: 添加部门
     * @Params: 
     */
    void addDep(Department dep);

    void delDep(Department dep);
}
