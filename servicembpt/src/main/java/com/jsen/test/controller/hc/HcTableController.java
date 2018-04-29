package com.jsen.test.controller.hc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.service.HcTableService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/16
 */
@RestController
@CrossOrigin
@RequestMapping("/hcTable")
public class HcTableController {

    @Autowired
    HcTableService hcTableService;

    @GetMapping("/table/list")
    public ResponseBase listAll() {
        return hcTableService.listTables();
    }

    @GetMapping("/table/update")
    public ResponseBase updateTable(@RequestParam("id") Integer id,
                                    @RequestParam("metaName") String metaName,
                                    @RequestParam("comment") String comment,
                                    @RequestParam("tag") String tag
    ) {
        return hcTableService.updateHcTable(id, metaName, comment, tag);
    }

    @GetMapping("/table/delete/{id}")
    public ResponseBase updateTable(
            @PathVariable("id") Integer id) {
        return hcTableService.deleteHcTable(id);
    }

    @GetMapping("/table/insert/{tableName}/{metaName}/{comment}/{tag}")
    public ResponseBase insertTable(
            @PathVariable("tableName") String tableName,
            @PathVariable("metaName") String metaName,
            @PathVariable("comment") String comment,
            @PathVariable("tag") String tag
    ) {
        return hcTableService.insertHcTable(tableName, metaName, comment, tag);
    }

    @GetMapping("/table/role/{tableId}/{roleId}")
    public ResponseBase tableAddRole(
            @PathVariable("tableId") Integer tableId,
            @PathVariable("roleId") Integer roleId
    ) {
        return hcTableService.tableAddRole(tableId, roleId);
    }

    @GetMapping("/tables/role/{tableIds}/{roleId}")
    public ResponseBase tableAddRoles(
            @PathVariable("tableIds") String tableIds,
            @PathVariable("roleId") Integer roleId
    ) {
        try {
            JSONArray array = JSON.parseArray(tableIds);
            return hcTableService.tablesAddRole(array, roleId);
        } catch (Exception e) {
            return ConstantResponse.FAIL.msg("JSON格式转换出错");
        }
    }

    @GetMapping("/table/topic/{tableIds}/{topicId}")
    public ResponseBase tableAddTopic(
            @PathVariable("tableIds") String tableIds,
            @PathVariable("topicId") Integer topicId
    ) {
        return hcTableService.tablesAddTopic(tableIds, topicId);
    }

    @GetMapping("/delete/rat/{rid}/{tid}")
    public ResponseBase deleteTableToRole(@PathVariable("rid") int rid, @PathVariable("tid") int tid) {
        return hcTableService.deleteRoleTable(rid, tid);
    }


}
