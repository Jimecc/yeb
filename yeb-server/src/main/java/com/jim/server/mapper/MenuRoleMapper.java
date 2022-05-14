package com.jim.server.mapper;

import com.jim.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * @Author: Jim
     * @Description: 批量更新
     * @Params:
     */
    Integer insertRecord(Integer rid, Integer[] mids);
}
