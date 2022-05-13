package com.jim.server.mapper;

import com.jim.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @Author: Jim
     * @Description: 根据用户 id 获取角色
     * @Params:
     */
    List<Role> getRoles(Integer adminId);
}
