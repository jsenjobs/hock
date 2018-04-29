package com.jsen.test.utils.modelcore.common.logical.model.relation;

import com.jsen.test.utils.modelcore.model.Node;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 *
 * 还未实现
 */
public class ModeLeftExclude extends JoinModelBase {
    @Override
    public String genSQL(Node node) {
        if (!findParents(node)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        String actSourceTable = getTableName(_sourceNode);
        String actTargetTable = getTableName(_targetNode);
        String sourcePrefix = actSourceTable + ".";
        String targetPrefix = actTargetTable + ".";
        String sCs = createSqlParamSplice(sourceTableShowColumns, sourcePrefix, " AS source_");
        if(sCs.trim().length() > 0) {
            builder.append(sCs);
        } else {
            return "";
        }
        builder.append(" FROM ").append(actSourceTable).append(" INNER JOIN ").append(actTargetTable).append(" ON ")
        .append(createFilter(sourceTableColumns, targetTableColumns, sourcePrefix, targetPrefix, funcs));

        return builder.toString();
    }
}
