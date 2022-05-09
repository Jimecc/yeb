package com.jim.server.service.impl;

import com.jim.server.pojo.Role;
import com.jim.server.mapper.RoleMapper;
import com.jim.server.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
