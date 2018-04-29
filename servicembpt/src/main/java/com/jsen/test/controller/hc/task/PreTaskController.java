package com.jsen.test.controller.hc.task;

import com.jsen.test.service.ScsWorkService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/19
 */
@RestController
@CrossOrigin
@RequestMapping("/task/pre")
class PreTaskController {

    @Autowired
    ScsWorkService scsWorkService;

    // 显示数据源中的表数据
    @GetMapping("/table/list/{tableName}/{page}/{limit}")
    public ResponseBase listTableData(@PathVariable("tableName") String tableName, @RequestParam(value = "query", required = false) String query,
                               @PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        return scsWorkService.listTableData(tableName, page, limit, query);
    }

}
