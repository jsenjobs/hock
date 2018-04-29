package com.jsen.test.service;

import com.jsen.test.entity.Link;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
public interface LinkService extends IService<Link> {

    ResponseBase listAll();

}
