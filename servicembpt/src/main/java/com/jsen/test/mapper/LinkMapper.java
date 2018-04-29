package com.jsen.test.mapper;

import com.jsen.test.entity.Link;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.union.LinkJoinAll;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
public interface LinkMapper extends BaseMapper<Link> {
    List<LinkJoinAll> listLinkJoinAll();

    List<Link> findLinksByWID(int w_id);
}
