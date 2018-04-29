package com.jsen.test.service;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public interface HcModelShareService {

    /**
     *
     * @param id sys_user id
     * @return
     */
    ResponseBase listAll(Integer id);
    ResponseBase save(byte[] modelData, int creator, String name, String intro, Integer type);
    ResponseBase save(int modelId, int creator, String name, String intro, Integer type);

    ResponseBase updateModelBy(String shareModelName, Integer creatorId, Integer modelId);


    ResponseBase deleteShareModelById(Integer id);

    /**
     * id是share model数据表的ID
     * @param id
     * @return
     */
    ResponseBase execModel(Integer id, JSONObject dynamicValues);
}
