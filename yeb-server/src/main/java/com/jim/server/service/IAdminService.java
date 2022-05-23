package com.jim.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jim.server.pojo.Admin;
import com.jim.server.pojo.RespBean;
import com.jim.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jim
 * @since 2022-05-11
 */
public interface IAdminService extends IService<Admin> {

    /**
     * @Author: Jim
     * @Description: 登陆之后返回 token
     * @Params: 
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * @Author: Jim
     * @Description: 根据用户名获取用户
     * @Params: 
     */
    Admin getAdminByUsername(String username);

    /**
     * @Author: Jim
     * @Description: 根据用户 id 查询角色列表
     * @Params: 
     */
    List<Role> getRoles(Integer adminId);

    /**
     * @Author: Jim
     * @Description: 获取所有操作员
     * @Params: 
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * @Author: Jim
     * @Description: 更新操作员角色
     * @Params: 
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
