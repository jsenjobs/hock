package com.jsen.test.utils.modelcore;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/22
 *
 * 通过数据表名字和tableFilters配置，获取相应的sql语句
 * 创建filter的sql部分
 */
public class ModuleColumnFilter {
    private interface KEYS {
        String UUID = "uuid";
        String IS_GROUP = "isGroup";
        String LEFT = "left";
        String RIGHT = "right";
        String _LEFT = "_left";
        String _RIGHT = "_right";
        String FUNC = "func";
    }
    private static final Map<String, String> funcMaps;
    static {
        funcMaps = Maps.newHashMap();
        funcMaps.put("eq", "=");
        funcMaps.put("lt", "<");
        funcMaps.put("gt", ">");
        funcMaps.put("gteq", ">=");
        funcMaps.put("lteq", "<=");
        funcMaps.put("noteq", "<>");
        funcMaps.put("and", "AND");
        funcMaps.put("or", "OR");
    }

    public static class BadFilterException extends RuntimeException {
        public BadFilterException(String message) {
            super(message);
        }
    }

    @Data
    @Accessors(chain = true)
    private static class FilterModel {
        /**
         * 标识
         */
        String uuid;
        /**
         * 是否设置为组
         */
        boolean isGroup = false;
        /**
         * 右边值
         */
        String left;
        /**
         * 左边值
         */
        String right;
        /**
         * 比较方法
         */
        String func;
        /**
         * isGroup为true 时左边的树
         */
        List<FilterModel> _left;
        /**
         * isGroup为true 时右边的树
         */
        List<FilterModel> _right;

        /**
         * 标识filter数据格式是否正确，不正确将出现解析失败
         */
        boolean badFilter = false;

        FilterModel(JSONObject jsonObject) {
            if(jsonObject.containsKey(KEYS.UUID) &&
                    jsonObject.containsKey(KEYS.IS_GROUP) &&
                    jsonObject.containsKey(KEYS.LEFT) &&
                    jsonObject.containsKey(KEYS.RIGHT) &&
                    jsonObject.containsKey(KEYS.FUNC) &&
                    jsonObject.containsKey(KEYS._LEFT) &&
                    jsonObject.containsKey(KEYS._RIGHT)) {
                this.uuid = jsonObject.getString(KEYS.UUID);
                this.isGroup = jsonObject.getBoolean(KEYS.IS_GROUP);
                this.left = jsonObject.getString(KEYS.LEFT);
                this.right = jsonObject.getString(KEYS.RIGHT);
                this.func = jsonObject.getString(KEYS.FUNC);

                if (this.isGroup) {
                    JSONArray _left = jsonObject.getJSONArray(KEYS._LEFT);
                    JSONArray _right = jsonObject.getJSONArray(KEYS._RIGHT);
                    if (_left.size() > 0) {
                        this._left = Lists.newArrayList();
                        for (int i = 0; i < _left.size(); i++) {
                            this._left.add(new FilterModel(_left.getJSONObject(i)));
                        }
                    }
                    if (_right.size() > 0) {
                        this._right = Lists.newArrayList();
                        for (int i = 0; i < _right.size(); i++) {
                            this._right.add(new FilterModel(_right.getJSONObject(i)));
                        }
                    }
                } else {
                    this.left = jsonObject.getString(KEYS.LEFT);
                    this.right = jsonObject.getString(KEYS.RIGHT);
                }

            } else {
                throw new BadFilterException("filter s数据不完整：" + jsonObject.toString());
            }
        }

        @Override
        public String toString() {
            if(!this.isGroup) {
                if(StringUtils.isEmpty(left) || StringUtils.isEmpty(right)) return "";
                return left + " " + funcMaps.get(func) + " '" + right + "'";
            } else if (this._left != null && this._right != null){
                StringBuilder sql = new StringBuilder();
                int len = this._left.size();
                sql.append("((");
                for(int i = 0; i < len; i++) {
                    if (i < len - 1) {
                        sql.append(_left.get(i).toString()).append(" and ");
                    } else {
                        sql.append(_left.get(i).toString());
                    }
                }
                sql.append(") ").append(funcMaps.get(func)).append(" (");
                len = this._right.size();
                for(int i = 0; i < len; i++) {
                    if (i < len - 1) {
                        sql.append(_right.get(i).toString()).append(" and ");
                    } else {
                        sql.append(_right.get(i).toString());
                    }
                }
                sql.append("))");
                return sql.toString();
            } else {
                return "";
            }
        }
    }

    public static String parseFilterByTable(JSONObject tableFilters, String tableName) {
        if (tableFilters.containsKey(tableName)) {
            JSONArray jsonArray = tableFilters.getJSONArray(tableName);
            return parseFilterByTable(jsonArray);
        } else {
            return "";
        }
    }

    public static String parseFilterByTable(JSONArray tableFilter) {
        if (tableFilter == null) {
            return "";
        }
        int size = tableFilter.size();
        if(size > 0) {
            List<FilterModel> filterModelLists = Lists.newArrayList();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < size; i++) {
                FilterModel fM = new FilterModel(tableFilter.getJSONObject(i));
                filterModelLists.add(fM);
                if(i < size - 1) {
                    builder.append(fM.toString()).append(" and ");
                } else {
                    builder.append(fM.toString());
                }
            }
            return builder.toString();
        } else {
            return "";
        }
    }


}
