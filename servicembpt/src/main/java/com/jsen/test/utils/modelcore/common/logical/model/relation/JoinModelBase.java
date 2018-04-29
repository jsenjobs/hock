package com.jsen.test.utils.modelcore.common.logical.model.relation;

import com.alibaba.fastjson.JSONArray;
import com.jsen.test.utils.modelcore.common.ModelType;
import com.jsen.test.utils.modelcore.common.logical.model.CalcModeBase;
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
public abstract class JoinModelBase extends CalcModeBase {
    String resultCollectionName;
    /**
     * part1
     */
    JSONArray keys;
    JSONArray sourceTableColumns;
    JSONArray funcs;
    JSONArray targetTableColumns;
    JSONArray sourceTableShowColumns;
    JSONArray targetTableShowColumns;

    String sourceTableName;
    String targetTableName;

    Node _sourceNode;
    Node _targetNode;

    String getTableName(Node parent) {
        if(parent.getModelType() == ModelType.DataSource) {
            return parent.getViewTableName().trim();
        } else {
            return parent.getTableName().trim();
        }
    }
    boolean findParents(Node node) {
        if (node.getParents() == null || node.getParents().size() != 2 || sourceTableName == null || targetTableName == null) {
            return false;
        }
        Node node1 = node.getParents().get(0);
        Node node2 = node.getParents().get(1);
        if (sourceTableName.equals(node1.getTableName())) {
            _sourceNode = node1;
        } else if (targetTableName.equals(node1.getTableName())) {
            _targetNode = node1;
        }
        if (sourceTableName.equals(node2.getTableName())) {
            _sourceNode = node2;
        } else if (targetTableName.equals(node2.getTableName())) {
            _targetNode = node2;
        }
        if (_sourceNode == null || _targetNode == null) {
            _sourceNode = _targetNode = null;
            return false;
        }
        return true;
    }
}
