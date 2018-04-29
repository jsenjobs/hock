package com.jsen.test.service;

import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/20
 */
public interface HcModelCoreTaskService {
    ResponseBase execPart(Integer id, String uuid);

    ResponseBase execModel(Integer modelId);
}
