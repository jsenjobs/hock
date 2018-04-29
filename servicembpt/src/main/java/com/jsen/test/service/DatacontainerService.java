package com.jsen.test.service;

import com.jsen.test.entity.Datacontainer;
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
public interface DatacontainerService extends IService<Datacontainer> {

    ResponseBase insertBatch(int num, boolean trans);
    ResponseBase insertBatch2(int num);
    ResponseBase insertBatch3(int num);
    ResponseBase insert(int num, boolean trans);

    ResponseBase transTimeout(int num, long sleep);
    ResponseBase transReadOnly(int num);
    ResponseBase transNoRollBackException(int num);


    ResponseBase wrInserts(int num);
    ResponseBase wrUpdate(int id, String data);
    ResponseBase wrDelete(int id);
    ResponseBase wrList();
}
