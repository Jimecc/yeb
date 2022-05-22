package com.jim.server.mapper;

import com.jim.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jim.server.pojo.RespBean;

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
    RespBean addDep(Department dep);

    RespBean delDep(Department dep);
}
