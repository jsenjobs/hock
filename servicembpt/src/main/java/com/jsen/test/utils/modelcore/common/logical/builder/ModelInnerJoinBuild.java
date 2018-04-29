package com.jsen.test.utils.modelcore.common.logical.builder;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
import com.jsen.test.utils.modelcore.common.logical.model.relation.ModeInnerJoin;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/24
 */
public class ModelInnerJoinBuild extends AbstractBuild {
    @Override
    public CalcModeBase build(JSONObject jsonObject) {
        ModeInnerJoin modeInnerJoin = new ModeInnerJoin();
        modeInnerJoin.setResultCollectionName(jsonObject.getString(ConfKeys.resultCollectionName));

        modeInnerJoin.setKeys(jsonObject.getJSONArray(ConfKeys.keys));
        modeInnerJoin.setSourceTableColumns(jsonObject.getJSONArray(ConfKeys.sourceTableColumns));
        modeInnerJoin.setFuncs(jsonObject.getJSONArray(ConfKeys.funcs));
        modeInnerJoin.setTargetTableColumns(jsonObject.getJSONArray(ConfKeys.targetTableColumns));
        modeInnerJoin.setSourceTableShowColumns(jsonObject.getJSONArray(ConfKeys.sourceTableShowColumns));
        modeInnerJoin.setTargetTableShowColumns(jsonObject.getJSONArray(ConfKeys.targetTableShowColumns));

        modeInnerJoin.setSourceTableName(jsonObject.getString(ConfKeys.sourceTableName));
        modeInnerJoin.setTargetTableName(jsonObject.getString(ConfKeys.targetTableName));
        return modeInnerJoin;
    }
}
