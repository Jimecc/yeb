package com.jim.server.service.impl;

import com.jim.server.pojo.Department;
import com.jim.server.mapper.DepartmentMapper;
import com.jim.server.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
