package com.jsen.test.service;

import com.alibaba.fastjson.JSONArray;
import com.jsen.test.entity.HcTable;
import com.jsen.test.utils.ResponseBase;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/11
 */
public interface HcTableService {
    List<HcTable> listTablesByTopic(int id, int userId);

    ResponseBase listTables();

    ResponseBase updateHcTable(Integer id, String metaName, String comment, String tag);

    ResponseBase deleteHcTable(Integer id);

    ResponseBase insertHcTable(String tableName, String metaName, String comment, String tag);

    ResponseBase tableAddRole(Integer tableId, Integer roleId);
    ResponseBase tablesAddRole(JSONArray tableIds, Integer roleId);
    ResponseBase tablesAddTopic(String tableIds, Integer topicId);

    ResponseBase deleteRoleTable(int roleId, int tableId);

}
