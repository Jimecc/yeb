package com.jim.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Jim
 * @Description Websocket消息类
 * @createTime 2022年05月26日
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain=true)
public class ChatMsg {

    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String fromNickName;
}
