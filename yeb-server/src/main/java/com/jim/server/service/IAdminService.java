package com.jim.server.service;

import com.jim.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jim.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

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
}
