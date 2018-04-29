package com.jsen.test.utils.modelcore.common.logical.builder;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
import com.jsen.test.utils.modelcore.common.logical.model.ModelAddColumn;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/24
 */
public class ModelAddColumnBuild extends AbstractBuild {
    @Override
    public CalcModeBase build(JSONObject jsonObject) {
        ModelAddColumn model = new ModelAddColumn();
        model.setResultCollectionName(jsonObject.getString(ConfKeys.resultCollectionName));

        model.setKeys(jsonObject.getJSONArray(ConfKeys.keys));
        model.setNames(jsonObject.getJSONArray(ConfKeys.names));
        model.setFuncs(jsonObject.getJSONArray(ConfKeys.funcs));
        return model;
    }
}
