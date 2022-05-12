package com.jim.server.mapper;

import com.jim.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jim.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Menu> getMenusByAdminId(Integer id);
}
