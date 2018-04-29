package com.jsen.test.service;

import com.jsen.test.entity.SysFilterChain;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysFilterChainService {

    void reloadFilterChain();

    List<SysFilterChain> listAll();

    ResponseBase createFilter(String url, String filters, int sort);
    ResponseBase deleteByUrl(String url);

    ResponseBase lists();
}
