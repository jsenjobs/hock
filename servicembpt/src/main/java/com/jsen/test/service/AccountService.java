package com.jsen.test.service;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.entity.Account;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
public interface AccountService {

    Account getAccountById(int id);

    JSONObject login(String domain, String token);

    JSONObject create(String domain, String token, String sex);

    ResponseBase listAccountJoinWeibos();
    // 使用join完成一对多映射
    ResponseBase listAccountJoinWeibosUseJoin();
    ResponseBase listAccountAll();
    ResponseBase listAccountAllUseJoin();

    ResponseBase listFlat();

    // 显示视图中的数据
    ResponseBase listInfoInView(String name);

    ResponseBase listBetween(int id1, int id2);

    ResponseBase listLimit(int offset, int limit);

    ResponseBase deleteDistinct();

    ResponseBase randomList();


    ResponseBase updateNameById(int id, String name);
    ResponseBase updateId(int id, int newId);
    ResponseBase deleteById(int id);

    ResponseBase listDb1();
    ResponseBase listDb2();
    ResponseBase listDPage();

}
