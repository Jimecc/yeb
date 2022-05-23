package com.jim.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jim.server.pojo.AdminRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(Integer adminId, Integer[] rids);
}
