package com.jsen.test.controller.sql;


import com.jsen.test.service.AccountService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/sql2")
// 复合查询
public class SqlPage {

    @Autowired
    AccountService accountService;
    @GetMapping("/page/{page}/{limit}")
    public ResponseBase page(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        int offset = (page - 1) * limit;
        return accountService.listLimit(offset, limit).msg("分页查询");
    }
    @GetMapping("/delete")
    public ResponseBase page() {
        return accountService.deleteDistinct().msg("删除名字重复的记录");
    }
    @GetMapping("/random")
    public ResponseBase random() {
        return accountService.randomList().msg("随机选取");
    }
}
