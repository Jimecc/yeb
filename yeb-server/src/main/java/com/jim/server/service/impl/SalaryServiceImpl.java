package com.jim.server.service.impl;

import com.jim.server.pojo.Salary;
import com.jim.server.mapper.SalaryMapper;
import com.jim.server.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jim
 * @since 2022-05-09
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

}
