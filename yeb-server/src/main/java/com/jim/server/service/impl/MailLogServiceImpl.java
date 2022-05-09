package com.jim.server.service.impl;

import com.jim.server.pojo.MailLog;
import com.jim.server.mapper.MailLogMapper;
import com.jim.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jim
 * @since 2022-05-09
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
