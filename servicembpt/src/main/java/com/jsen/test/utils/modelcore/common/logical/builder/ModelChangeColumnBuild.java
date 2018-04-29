package com.jsen.test.utils.modelcore.common.logical.builder;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
import com.jsen.test.utils.modelcore.common.logical.model.ModelAddColumn;
import com.jsen.test.utils.modelcore.common.logical.model.ModelChangeColumn;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/24
 */
public class ModelChangeColumnBuild extends AbstractBuild {
    @Override
    public CalcModeBase build(JSONObject jsonObject) {
        ModelChangeColumn model = new ModelChangeColumn();
        model.setResultCollectionName(jsonObject.getString(ConfKeys.resultCollectionName));

        model.setChangeColumns(jsonObject.getJSONArray(ConfKeys.changeColumns));
        return model;
    }
}
