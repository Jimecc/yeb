package com.jim.server.service.impl;

import com.jim.server.pojo.Nation;
import com.jim.server.mapper.NationMapper;
import com.jim.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
