package com.jsen.test.utils.modelcore.common.logical.model;

import com.alibaba.fastjson.JSONArray;
import com.jsen.test.utils.modelcore.ModuleColumnFilter;
import com.jsen.test.utils.modelcore.common.ModelType;
import com.jsen.test.utils.modelcore.model.Node;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/24
 */
@Data
@Accessors(chain = true)
public class ModelAddColumn extends CalcModeBase {
    String resultCollectionName;

    JSONArray keys;
    JSONArray names;
    JSONArray funcs;

    private String getTableName(Node parent) {
        if(parent.getModelType() == ModelType.DataSource) {
            return parent.getViewTableName();
        } else {
            return parent.getTableName();
        }
    }

    @Override
    public String genSQL(Node node) {

        if (node.getParents() == null || node.getParents().size() != 1 || keys == null || keys.size() == 0) {
            return "";
        }

        String filterSQL = "";
        if (node.getTableFilter() != null) {
            String q = ModuleColumnFilter.parseFilterByTable(node.getTableFilter());
            if (q.trim().length() > 0) {
                filterSQL = " WHERE " + q;
            }
        }
        String actTableName = getTableName(node.getParents().get(0));
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT *,").append(createSqlParamWithAsColumn(keys, funcs, names)).append(" FROM ").append(actTableName).append(filterSQL);

        return builder.toString();
    }
}
