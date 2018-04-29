package com.jsen.test.service;

import com.jsen.test.entity.Weibo;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
public interface WeiboService extends IService<Weibo> {

    ResponseBase getWeibos(String name);

}
