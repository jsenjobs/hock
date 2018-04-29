package com.jsen.test.service.impl;

import com.jsen.test.entity.Link;
import com.jsen.test.mapper.LinkMapper;
import com.jsen.test.service.LinkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseBase listAll() {
        long t1 = System.currentTimeMillis();
        List l = baseMapper.listLinkJoinAll();
        System.out.println(System.currentTimeMillis() - t1);
        return ResponseBase.create().code(0).add("data", l);
    }
}
