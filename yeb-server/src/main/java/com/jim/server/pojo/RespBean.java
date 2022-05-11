package com.jim.server.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    
    private long code;
    private String message;
    private Object obj;
    
    
    /**
     * @Author: Jime.cc
     * @Description: 成功访问
     * @Params: message
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    
    /**
     * @Author: Jime.cc
     * @Description: 成功返回信息
     * @Params: message obj
     */
    public static RespBean success (String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     * @Author: Jime.cc
     * @Description: 失败返回信息
     * @Params: message
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * 失败返回信息
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}
