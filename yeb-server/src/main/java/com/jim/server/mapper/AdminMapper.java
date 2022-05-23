package com.jim.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jim.server.pojo.Admin;
import org.apache.ibatis.annotations.Param;

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

   
    /**
     * @Author: Jim
     * @Description: 获取所有操作员
     * @Params: 
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
