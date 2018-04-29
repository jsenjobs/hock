package com.jsen.test.mapper;

import com.jsen.test.entity.Weibo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.union.WeiboAll;
import com.jsen.test.entity.union.WeiboJoinAccount;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
public interface WeiboMapper extends BaseMapper<Weibo> {
    List<WeiboJoinAccount> findWeiboJoinAccount(Weibo weibo);

    List<Weibo> findWeibosByAID(int id);

    List<WeiboAll> findAllWeibosByAID(int id);
}
