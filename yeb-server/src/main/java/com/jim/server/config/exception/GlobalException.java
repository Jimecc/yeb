package com.jim.server.config.exception;

import com.jim.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: Jim
 * @Description: 全局异常处理
 * @Params:
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public RespBean mySsqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("关联数据表错误，操作失败！");
        }
        return RespBean.error("数据库异常，操作失败");
    }
}
