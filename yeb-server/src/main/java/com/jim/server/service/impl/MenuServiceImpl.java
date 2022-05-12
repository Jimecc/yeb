package com.jim.server.service.impl;

import com.jim.server.pojo.Admin;
import com.jim.server.pojo.Menu;
import com.jim.server.mapper.MenuMapper;
import com.jim.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Autowired
    private MenuMapper menuMapper;

    /**
     * @Author: Jim
     * @Description: 根据用户id 查询菜单列表
     * @Params:
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        return menuMapper.getMenusByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
