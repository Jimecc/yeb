package com.jim.server.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Jim
 * @Description: 分页公共返回对象
 * @Params:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据 list
     */
    private List<?> data;
}
