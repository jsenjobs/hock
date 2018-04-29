package com.jsen.test.utils.modelcore.common.logical.builder;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public abstract class AbstractBuild {
    public abstract CalcModeBase build(JSONObject jsonObject);
}
