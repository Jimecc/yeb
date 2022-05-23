package com.jim.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jim.server.mapper.EmployeeMapper;
import com.jim.server.pojo.Employee;
import com.jim.server.pojo.RespBean;
import com.jim.server.pojo.RespPageBean;
import com.jim.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * @Author: Jim
     * @Description: 获取所有员工（分页）
     * @Params: 
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page,employee,beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(),employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * @Author: Jim
     * @Description: 获取最大工号
     */
    @Override
    public RespBean maxWorkID() {
        List<Map<String,Object>> maps =  employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        String maxWorkID = String.format("%08d",Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1 );
        return RespBean.success("查询成功",maxWorkID);
    }

    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限（保留两位小数）
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        if(1 == employeeMapper.insert(employee)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }
}
