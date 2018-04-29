package com.jsen.test.service;

import com.alibaba.fastjson.JSONArray;
import com.jsen.test.utils.ResponseBase;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/26
 */
public interface ScsWorkService {

    List exec(List<String> sqls, String sql);


    // list meta


    ResponseBase listColumns(String tableName);
    ResponseBase listTables(String dbName);

    ResponseBase listTableData(String tableName, Integer page, Integer limit, String query);


    // 删除视图
    ResponseBase delViewTableByName(String name);
    ResponseBase delViewTablesByName(JSONArray nameList);

}
