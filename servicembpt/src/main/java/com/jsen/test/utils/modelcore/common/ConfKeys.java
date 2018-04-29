package com.jsen.test.utils.modelcore.common;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public interface ConfKeys {
    String datas = "datas";
    String unionDatas = "unionDatas";
    String links = "links";
    String TABLE_FILTERS = "tableFilters";

    String id = "id";
    String source = "source";
    String target = "target";
    String workConf = "_workConf";
    String type = "type";

    // work conf
    String tableName = "tableName";
    String viewTableName = "viewTableName";

    // ModeAggregation
    String calFunc = "calFunc";
    String columns = "columns";
    String column = "column";
    String n_column = "n_column";
    String funcs = "funcs";
    String keys = "keys";
    String names = "names";
    String resultCollectionName = "resultCollectionName";
    String changeColumns = "changeColumns";

    String groupColumns = "groupColumns";
    String notGroupColumns = "notGroupColumns";
    String useNotGroupColumn = "useNotGroupColumn";

    // inner join
    String sourceTableColumns = "sourceTableColumns";
    String targetTableColumns = "targetTableColumns";
    String sourceTableShowColumns = "sourceTableShowColumns";
    String targetTableShowColumns = "targetTableShowColumns";
    String sourceTableName = "sourceTableName";
    String targetTableName = "targetTableName";

    // filters

    String isGroup = "isGroup";
    String left = "left";
    String right = "right";
    String _left = "_left";
    String _right = "_right";
    String func = "func";
    String dynamicName = "dynamicName";
    String isDynamicValue = "isDynamicValue";
    String value = "value";
}
