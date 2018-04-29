package com.jsen.test.controller.hc.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.service.HcModelCoreTaskService;
import com.jsen.test.service.ScsWorkService;
import com.jsen.test.service.SqlCreatorService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/20
 */
@RestController
@CrossOrigin
@RequestMapping("/task/core")
class CoreTaskController {
    @Autowired
    HcModelCoreTaskService hcModelCoreTaskService;
    @Autowired
    ScsWorkService scsWorkService;
    @Autowired
    SqlCreatorService sqlCreatorService;

    /**
     * 通过模型ID和要执行的模型节点ID（UUID）执行模型
     * @param id 数据库中模型ID
     * @param uuid 模型节点ID
     * @return
     */
    @GetMapping("/part/exec")
    public ResponseBase execPart(@RequestParam("id") Integer id, @RequestParam("uuid") String uuid) {
        return hcModelCoreTaskService.execPart(id, uuid);
    }
    @GetMapping("/exec")
    public ResponseBase execAll(@RequestParam("id") String id) {
        return null;
    }



    @GetMapping("/run/{currentModelID}/{UUID}")
    public ResponseBase genSqls(@PathVariable("currentModelID") Integer currentModelID, @PathVariable("UUID") String UUID) {
        return ResponseBase.create().code(0).data(sqlCreatorService.genSQL(currentModelID, UUID).getData());
    }

    /**
     * 通过视图名字删除视图
     * @param viewName 视图名字
     * @return
     */
    @GetMapping("/view/del/{viewName}")
    public ResponseBase delViewTable(@PathVariable("viewName") String viewName) {
        try {
            JSONArray array = JSON.parseArray(viewName);
            return scsWorkService.delViewTablesByName(array);
        } catch (Exception e) {
            return scsWorkService.delViewTableByName(viewName);
        }
    }



    /**
     *
     * @param id 当前模型ID
     * @return
     */
    @GetMapping("/model/exec/{id}")
    public ResponseBase execShareModel(@PathVariable("id") Integer id) {
        return hcModelCoreTaskService.execModel(id);
    }

}
