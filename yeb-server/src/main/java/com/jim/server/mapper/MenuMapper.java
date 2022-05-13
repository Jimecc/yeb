package com.jim.server.mapper;

import com.jim.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * @Author: Jim
     * @Description: 根据用户 id 查询菜单列表
     * @Params: 
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * @Author: Jim
     * @Description: 根据角色获取菜单列表
     * @Params:
     */
    List<Menu> getMenusWithRole();
}
