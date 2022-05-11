package com.jim.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jim
 * @Description: 公共返回对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;
    
    /**
     * @Author: Jim
     * @Description: 成功返回结果
     * @Params: 
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null );
    }


    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }
    
    /**
     * @Author: Jim
     * @Description: 失败返回结果
     * @Params: 
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}
