package com.jsen.test.utils.modelcore.common.logical.model.relation;

import com.jsen.test.utils.modelcore.model.Node;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public class ModeFullJoin extends JoinModelBase {


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
        String sCs = createSqlParamSplice(sourceTableShowColumns, sourcePrefix, " AS " + _sourceNode.getTableName());
        String tCs = createSqlParamSplice(targetTableShowColumns, targetPrefix, " AS " + _targetNode.getTableName());
        if(sCs.trim().length() > 0) {
            if (tCs.trim().length() > 0) {
                builder.append(sCs).append(",").append(tCs);
            } else {
                builder.append(sCs);
            }
        } else {
            if (tCs.trim().length() > 0) {
                builder.append(tCs);
            } else {
                return "";
            }
        }
        builder.append(" FROM ").append(actSourceTable);
        StringBuilder rightBuild = new StringBuilder(builder);
        builder.append(" LEFT JOIN ").append(actTargetTable).append(" ON ")
                .append(createFilter(sourceTableColumns, targetTableColumns, sourcePrefix, targetPrefix, funcs));
        rightBuild.append(" RIGHT JOIN ").append(actTargetTable).append(" ON ")
                .append(createFilter(sourceTableColumns, targetTableColumns, sourcePrefix, targetPrefix, funcs));

        return builder.append(" UNION ").append(rightBuild).toString();
    }

}
