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

    List<Menu> getMenuByAdminId();
}