package com.jim.server.service;

import com.jim.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jim.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * @Author: Jim
     * @Description: 更新角色菜单
     * @Params: 
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
