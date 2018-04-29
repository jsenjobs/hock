package com.jsen.test.utils.modelcore.common.logical.builder;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
import com.jsen.test.utils.modelcore.common.logical.model.ModeAggregation;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public class ModeAggregationBuild extends AbstractBuild {
    @Override
    public CalcModeBase build(JSONObject jsonObject) {
        ModeAggregation modeAggregation = new ModeAggregation();
        modeAggregation.setResultCollectionName(jsonObject.getString(ConfKeys.resultCollectionName));
        modeAggregation.setCalcFunc(ModeAggregation.Type.values()[jsonObject.getInteger(ConfKeys.calFunc)]);

        if (modeAggregation.getCalcFunc() == ModeAggregation.Type.GROUP || modeAggregation.getCalcFunc() == ModeAggregation.Type.TOTAL) {
            modeAggregation.setColumns(jsonObject.getJSONArray(ConfKeys.columns));
            modeAggregation.setFuncs(jsonObject.getJSONArray(ConfKeys.funcs));
            modeAggregation.setKeys(jsonObject.getJSONArray(ConfKeys.keys));
            modeAggregation.setNames(jsonObject.getJSONArray(ConfKeys.names));
        }
        if (modeAggregation.getCalcFunc() == ModeAggregation.Type.GROUP || modeAggregation.getCalcFunc() == ModeAggregation.Type.DISTINCT) {
            modeAggregation.setGroupColumns(jsonObject.getJSONArray(ConfKeys.groupColumns));
            modeAggregation.setUseNotGroupColumn(jsonObject.getBoolean(ConfKeys.useNotGroupColumn));
            modeAggregation.setNotGroupColumns(jsonObject.getJSONArray(ConfKeys.notGroupColumns));
        }
        return modeAggregation;
    }
}
