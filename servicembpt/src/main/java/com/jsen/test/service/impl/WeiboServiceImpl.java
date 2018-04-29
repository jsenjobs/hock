package com.jsen.test.service.impl;

import com.jsen.test.entity.Weibo;
import com.jsen.test.mapper.WeiboMapper;
import com.jsen.test.service.WeiboService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
@Service
public class WeiboServiceImpl extends ServiceImpl<WeiboMapper, Weibo> implements WeiboService {

    @Override
    public ResponseBase getWeibos(String name) {
        Weibo weibo = new Weibo().setCreateTime(new Date()).setContent(name);


        return ResponseBase.create().code(0).add("data", baseMapper.findWeiboJoinAccount(weibo));
    }
}
