package com.jim.server.service;

import com.jim.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface IMenuService extends IService<Menu> {

    /**
     * @Author: Jim
     * @Description: 根据用户 id 查询菜单列表
     * @Params: 
     */
    List<Menu> getMenuByAdminId();

    /**
     * @Author: Jim
     * @Description: 根据角色获取菜单列表
     * @Params: 
     */
    List<Menu> getMenusWithRole();

    /**
     * @Author: Jim
     * @Description: 查询所有菜单
     * @Params: 
     */
    List<Menu> getAllMenus();
}
