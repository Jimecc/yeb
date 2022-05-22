package com.jim.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jim.server.mapper.DepartmentMapper;
import com.jim.server.pojo.Department;
import com.jim.server.pojo.RespBean;
import com.jim.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *'、：
 * @author jim
 * @since 2022-05-11
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * @Author: Jim
     * @Description: 获取所有部门
     * @Params: 
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * @Author: Jim
     * @Description: 添加部门
     * @Params:
     */
    @Override
    public RespBean addDep(Department department) {
        department.setEnabled(true);
        departmentMapper.addDep(department);
        if(1 == department.getResult()) {
            return RespBean.success("添加成功",department);
        }
        return RespBean.error("添加失败");
    }

    /**
     * @Author: Jim
     * @Description: 删除部门
     * @Params: 
     */
    @Override
    public RespBean delDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.delDep(dep);
        if(-2 == dep.getResult()){
            return RespBean.error("该部门下还有子部门，删除失败！");
        }

        if(-1 == dep.getResult()){
            return RespBean.error("该部门下还有员工，删除失败！");
        }

        if(1 == dep.getResult()){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
