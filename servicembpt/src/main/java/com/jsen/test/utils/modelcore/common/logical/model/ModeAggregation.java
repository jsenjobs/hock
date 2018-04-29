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
 * @since 2018/4/23
 */
@Data
@Accessors(chain = true)
public class ModeAggregation extends CalcModeBase {
    public enum Type {
        // 分组统计
        GROUP,
        // 全表统计
        TOTAL,
        // 数据去重
        DISTINCT,

    }

    String resultCollectionName;

    Type calcFunc = Type.GROUP;

    /**
     * part1
     */
    JSONArray groupColumns;
    JSONArray notGroupColumns;
    boolean useNotGroupColumn = false;


    /**
     * part 2
     */
    JSONArray keys;

    JSONArray names;
    JSONArray funcs;
    JSONArray columns;


    private String getTableName(Node parent) {
        if(parent.getModelType() == ModelType.DataSource) {
            return parent.getViewTableName();
        } else {
            return parent.getTableName();
        }
    }
    @Override
    public String genSQL(Node node) {
        String func;
        String column;
        String name;
        StringBuilder builder = new StringBuilder();
        int len;
        if (node.getParents() == null || node.getParents().size() == 0) {
            return "";
        }

        String filterSQL = "";
        if (node.getTableFilter() != null) {
            String q = ModuleColumnFilter.parseFilterByTable(node.getTableFilter());
            if (q.trim().length() > 0) {
                filterSQL = " WHERE " + q;
            }
        }
        switch (calcFunc) {
            case GROUP:
                len = keys.size();
                if (len == 0) {
                    return "";
                }
                builder.append("SELECT ");
                for (int i = 0; i < len; i++) {
                    int index = keys.getInteger(i);
                    func = funcs.getString(index);
                    column = columns.getString(index);
                    name = names.getString(index);
                    builder.append(func).append("(").append(column).append(")").append(" AS ").append(name);
                    if (i < len - 1) {
                        builder.append(",");
                    }
                }
                if(notGroupColumns != null && useNotGroupColumn) {
                    len = notGroupColumns.size();
                    for (int i = 0; i < len; i++) {
                        builder.append(",").append(notGroupColumns.getString(i));
                    }
                }
                builder.append(" FROM ").append(getTableName(node.getParents().get(0))).append(filterSQL);
                if (groupColumns != null && groupColumns.size() > 0) {
                    builder.append(" GROUP BY ");
                    len = groupColumns.size();
                    for (int i = 0; i < len; i++) {
                        builder.append(groupColumns.getString(i));
                        if (i < len - 1) {
                            builder.append(",");
                        }
                    }
                }
                break;
            case TOTAL:
                len = keys.size();
                if (len == 0) {
                    return "";
                }
                builder.append("SELECT ");
                for (int i = 0; i < len; i++) {
                    int index = keys.getInteger(i);
                    func = funcs.getString(index);
                    column = columns.getString(index);
                    name = names.getString(index);
                    builder.append(func).append("(").append(column).append(")").append(" AS ").append(name);
                    if (i < len - 1) {
                        builder.append(",");
                    }
                }
                builder.append(" FROM ").append(getTableName(node.getParents().get(0))).append(filterSQL);
                break;
            case DISTINCT:
                builder.append("SELECT DISTINCT ");
                if (groupColumns != null && groupColumns.size() > 0) {
                    len = groupColumns.size();
                    for (int i = 0; i < len; i++) {
                        builder.append(groupColumns.getString(i));
                        if (i < len - 1) {
                            builder.append(",");
                        }
                    }
                    builder.append(" FROM ").append(getTableName(node.getParents().get(0))).append(filterSQL);
                } else {
                    return "";
                }
                break;
                default:
        }
        return builder.toString();
    }
}
