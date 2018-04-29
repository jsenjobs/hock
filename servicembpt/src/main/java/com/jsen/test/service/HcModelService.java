package com.jsen.test.service;

import com.jsen.test.entity.HcModel;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-04-16
 */
public interface HcModelService extends IService<HcModel> {
    ResponseBase create(String name, String intro, String modelData, int creator);
    ResponseBase updateById(int modelId, String name, String intro, String modelData);
    ResponseBase clearAllDataByUserId(int uId);

    ResponseBase findByUserId(int userId);

    ResponseBase updateModelNameByName(String oldModelName, String newModelName);
}
