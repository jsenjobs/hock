package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.entity.HcTable;
import com.jsen.test.entity.HcTheme;
import com.jsen.test.entity.SysPermission;
import com.jsen.test.entity.SysRolePermission;
import com.jsen.test.mapper.HcTableMapper;
import com.jsen.test.mapper.HcThemeMapper;
import com.jsen.test.service.HcTableService;
import com.jsen.test.service.HcThemeService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/11
 */
@Service
public class HcTableServiceImpl extends ServiceImpl<HcTableMapper, HcTable> implements HcTableService {
    @Override
    public List<HcTable> listTablesByTopic(int id, int userId) {
        return baseMapper.getTableByTopicIdAndUserId(id, userId);
    }

    @Override
    public ResponseBase listTables() {
        return ResponseBase.create().code(0).data(baseMapper.listTables());
    }

    @Override
    public ResponseBase updateHcTable(Integer id, String metaName, String comment, String tag) {
        HcTable hcTable = new HcTable();
        hcTable.setId(id);
        if (!StringUtils.isEmpty(metaName)) {
            hcTable.setMetaName(metaName);
        }
        if (!StringUtils.isEmpty(comment)) {
            hcTable.setComment(comment);
        }
        if (!StringUtils.isEmpty(tag)) {
            hcTable.setTag(tag);
        }
        return ResponseBase.create().code(0).add("eff", baseMapper.updateTable(hcTable));
    }

    @Override
    public ResponseBase deleteHcTable(Integer id) {
        baseMapper.deleteTableRoleByTableId(id);
        baseMapper.deleteTableTopicByTableId(id);
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteById(id));
    }

    @Override
    public ResponseBase insertHcTable(String tableName, String metaName, String comment, String tag) {
        return ResponseBase.create().code(0).add("eff", baseMapper.insertHcTable(new HcTable().setTableName(tableName).setMetaName(metaName).setComment(comment).setTag(tag)));
    }

    @Override
    public ResponseBase tableAddRole(Integer tableId, Integer roleId) {
        if (baseMapper.findExistRoleTable(tableId, roleId) > 0) {
            return ResponseBase.create().code(1).msg("关联已存在");
        }
        return ResponseBase.create().code(0).add("eff", baseMapper.insertRoleTable(tableId, roleId)).data(baseMapper.getTableById(tableId));
    }

    @Override
    public ResponseBase tablesAddRole(JSONArray tableIds, Integer roleId) {


        int eff = 0;
        JSONArray array = new JSONArray();
        for(int i = 0; i < tableIds.size(); i++) {
            int tableId = tableIds.getInteger(i);
            if (baseMapper.findExistRoleTable(tableId, roleId) == 0) {
                if (baseMapper.insertRoleTable(tableId, roleId) == 1) {
                    eff ++;
                    array.add(baseMapper.getTableById(tableId));
                }
            }
        }
        return ResponseBase.create().code(0).add("eff", eff).add("data", array);
    }

    @Override
    public ResponseBase tablesAddTopic(String tableIds, Integer topicId) {
        List<Integer> array;
        try {
            array = JSON.parseArray(tableIds).toJavaList(Integer.class);
            if (array.isEmpty()) {
                return ResponseBase.create().code(1).msg("请输入要关联的表");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg("tableIds json数据出错");
        }
        for (Integer tableId:array) {
            if (baseMapper.findExistTopicTable(tableId, topicId) > 0) {
                return ResponseBase.create().code(1).msg("关联已存在");
            }
        }
        return ResponseBase.create().code(0).add("eff", baseMapper.insertTopicTables(array, topicId));
    }

    @Override
    public ResponseBase deleteRoleTable(int roleId, int tableId) {
        return ResponseBase.create().code(0).add("eff", baseMapper.deleteRoleTableByRoleIdAndTableId(roleId, tableId));
    }
}
