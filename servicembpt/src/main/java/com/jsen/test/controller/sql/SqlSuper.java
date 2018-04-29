package com.jsen.test.controller.sql;


import com.jsen.test.service.AccountService;
import com.jsen.test.service.LinkService;
import com.jsen.test.service.WeiboService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// sql test
@RestController
@CrossOrigin
@RequestMapping("/sql")
public class SqlSuper {

    @Autowired
    WeiboService weiboService;
    @Autowired
    AccountService accountService;
    @Autowired
    LinkService linkService;

    @GetMapping("/union/weibojoinaccount/{user}")
    public ResponseBase union001(@PathVariable("user") String user) {
        return weiboService.getWeibos(user).msg("一对一，使用join");
    }

    @GetMapping("/union/accountjoinweibos")
    public ResponseBase union002() {
        return accountService.listAccountJoinWeibos().msg("一对多，不是join，直接select");
    }

    @GetMapping("/union/accountjoinweibosusejoin")
    public ResponseBase union003() {
        return accountService.listAccountJoinWeibosUseJoin().msg("一对多，join，这里出来的一对多，一的数据会分别映射到多条，符合一对多 left join 出现的结果");
    }




    @GetMapping("/union2/links")
    public ResponseBase union004() {
        return linkService.listAll().msg("一对一，多表join");
    }

    @GetMapping("/union2/accountAll")
    public ResponseBase union005() {
        return accountService.listAccountAll().msg("一对多，不是join，直接select");
    }

    @GetMapping("/union2/accountAllUseJoin")
    public ResponseBase union006() {
        return accountService.listAccountAllUseJoin().msg("一对多，join，这里出来的一对多，一的数据会分别映射到多条，符合一对多 left join 出现的结果");
    }



    @GetMapping("/union3/listflat")
    public ResponseBase union007() {
        return accountService.listFlat().msg("显示join的试图");
    }

    @GetMapping("/union/listInView/{name}")
    public ResponseBase showInView(@PathVariable(value = "name", required = false) String name) {
        return accountService.listInfoInView(name).msg("显示join的视图");
    }

    @GetMapping("/between/{id1}/{id2}")
    public ResponseBase showInView(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        return accountService.listBetween(id1, id2).msg("显示between之间的数据");
    }

}
