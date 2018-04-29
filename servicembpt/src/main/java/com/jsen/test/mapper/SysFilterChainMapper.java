package com.jsen.test.mapper;

import com.jsen.test.entity.SysFilterChain;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
@Service
public interface SysFilterChainMapper extends BaseMapper<SysFilterChain> {

    List<SysFilterChain> listAll();

    int deleteByUrl(String url);

    int insertFilter(SysFilterChain sysFilterChain);

    SysFilterChain getFilterByUrl(String url);

}
