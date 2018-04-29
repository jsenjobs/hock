package com.jsen.test.utils.modelcore.common.logical.builder;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
import com.jsen.test.utils.modelcore.common.logical.model.relation.ModeInnerJoin;
import com.jsen.test.utils.modelcore.common.logical.model.relation.ModeLeftJoin;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/24
 */
public class ModelLeftJoinBuild extends AbstractBuild {
    @Override
    public CalcModeBase build(JSONObject jsonObject) {
        ModeLeftJoin join = new ModeLeftJoin();
        join.setResultCollectionName(jsonObject.getString(ConfKeys.resultCollectionName));

        join.setKeys(jsonObject.getJSONArray(ConfKeys.keys));
        join.setSourceTableColumns(jsonObject.getJSONArray(ConfKeys.sourceTableColumns));
        join.setFuncs(jsonObject.getJSONArray(ConfKeys.funcs));
        join.setTargetTableColumns(jsonObject.getJSONArray(ConfKeys.targetTableColumns));
        join.setSourceTableShowColumns(jsonObject.getJSONArray(ConfKeys.sourceTableShowColumns));
        join.setTargetTableShowColumns(jsonObject.getJSONArray(ConfKeys.targetTableShowColumns));

        join.setSourceTableName(jsonObject.getString(ConfKeys.sourceTableName));
        join.setTargetTableName(jsonObject.getString(ConfKeys.targetTableName));
        return join;
    }
}
