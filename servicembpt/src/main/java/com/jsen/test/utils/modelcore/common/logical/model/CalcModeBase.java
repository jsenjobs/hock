package com.jsen.test.utils.modelcore.common.logical.model;

import com.alibaba.fastjson.JSONArray;
import com.jsen.test.utils.modelcore.model.Node;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public abstract class CalcModeBase {
    public abstract String genSQL(Node node);

    protected JSONArray getCombine(JSONArray ...columnList) {
        JSONArray result = new JSONArray();
        for (JSONArray array : columnList) {
            result.addAll(array);
        }
        return result;
    }

    protected JSONArray getCombinePrefix(String prefix, JSONArray ...columnList) {
        JSONArray result = new JSONArray();
        for (JSONArray array : columnList) {
            for (Object o : array) {
                result.add(prefix + o);
            }
        }
        return result;
    }

    /*
     * 相关参数的创建
     * start
     */
    protected String createSqlParamSplice(JSONArray columns) {
        return _createSqlParam(columns, "", " AS ", ",", 1);
    }

    protected String createSqlParamSplice(JSONArray columns, String prefix, String asSuffix) {
        return _createSqlParam(columns, prefix, asSuffix, ",", 1);
    }

    protected String createSqlParam(JSONArray columns, String asSuffix) {
        return _createSqlParam(columns, "", asSuffix, ",", 0);
    }

    protected String createSqlParam(JSONArray columns, String prefix, String asSuffix) {
        return _createSqlParam(columns, prefix, asSuffix, ",", 0);
    }

    private String _createSqlParam(JSONArray columns, String prefix, String asSuffix, String suffix, int spliceLength) {
        StringBuilder builder = new StringBuilder();
        for (Object item : columns) {
            builder.append(prefix).append(item).append(asSuffix).append(item).append(suffix);
        }
        String result = builder.toString();
        return result.substring(0, result.length() - spliceLength);
    }

    /**
     *
     * @param indexKeys 下面两个数组对应元素的索引集合
     * @param columns name
     * @param asColumns as name
     * @return
     */
    protected String createSqlParamWithAsColumn(JSONArray indexKeys, JSONArray columns, JSONArray asColumns) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < indexKeys.size(); i++) {
            builder.append(columns.getString(i)).append(" AS ").append(asColumns.getString(i)).append(",");

        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    /*
     * 相关参数的创建
     * end
     */

    /*
     * 过滤条件创建 a=v1 AND b=v2
     * start
     */
    protected String createFilter(JSONArray sourceColumns, JSONArray targetColumns, String sourcePrefix, String targetPrefix, JSONArray funcs) {
        return createFilter(sourceColumns, targetColumns, sourcePrefix, targetPrefix, funcs, " AND ");
    }
    /**
     *
     * @param sourceColumns
     * @param targetColumns
     * @param sourcePrefix
     * @param targetPrefix
     * @param split 分割符
     * @return
     */
    protected static String createFilter(JSONArray sourceColumns, JSONArray targetColumns, String sourcePrefix, String targetPrefix, JSONArray funcs, String split) {
        if (sourceColumns.size() != targetColumns.size() || sourceColumns.size() != funcs.size() || sourceColumns.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int len = sourceColumns.size();
        for (int i = 0; i < len; i++) {
            if (i < len - 1) {
                builder.append(sourcePrefix).append(sourceColumns.getString(i)).append(funcs.getString(i)).append(targetPrefix).append(targetColumns.getString(i)).append(split);
            } else {
                builder.append(sourcePrefix).append(sourceColumns.getString(i)).append(funcs.getString(i)).append(targetPrefix).append(targetColumns.getString(i));
            }
        }
        return builder.toString();
    }
}
