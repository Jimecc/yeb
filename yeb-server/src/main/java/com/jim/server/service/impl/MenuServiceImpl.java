package com.jim.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jim.server.utils.AdminUtils;
import com.jim.server.mapper.MenuMapper;
import com.jim.server.pojo.Menu;
import com.jim.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @Author: Jim
     * @Description: 根据用户id 查询菜单列表
     * @Params:
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_"+adminId);
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_"+adminId,menus);
        }

        return menus;
    }


    /**
     * @Author: Jim
     * @Description: 根据角色获取菜单列表
     * @Params:
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * @Author: Jim
     * @Description: 查询所有菜单
     * @Params:
     */
    @Override
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
}
