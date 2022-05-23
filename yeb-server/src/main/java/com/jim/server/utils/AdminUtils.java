package com.jim.server.utils;

import com.jim.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtils {
    
    /**
     * @Author: Jim
     * @Description: 获取当前登录操作员
     * @Params: 
     */
    public static Admin getCurrentAdmin(){
        return ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
