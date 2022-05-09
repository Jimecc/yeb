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
 * @since 2022-05-09
 */
public interface IAdminService extends IService<Admin> {


    /**
     * 登陆之后 返回 token
     * @param username 用户名
     * @param password 密码
     * @param request
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);


    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

}
