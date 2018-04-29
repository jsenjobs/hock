package com.jsen.test.controller.sql;


import com.jsen.test.service.AccountService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// account
@RestController
@CrossOrigin
@RequestMapping("/sqlc")
public class SqlConstraints {

    @Autowired
    AccountService accountService;

    @GetMapping("/update/name/{id}/{name}")
    public ResponseBase test001(@PathVariable("name") String name, @PathVariable("id") int id) {
        return accountService.updateNameById(id, name).msg("更新名字");
    }


    @GetMapping("/update/id/{id}/{nid}")
    public ResponseBase test002(@PathVariable("id") int id, @PathVariable("nid") int nid) {
        return accountService.updateId(id, nid).msg("更新名字");
    }


    @GetMapping("/delete/{id}")
    public ResponseBase test003(@PathVariable("id") int id) {
        return accountService.deleteById(id).msg("更新名字");
    }

}
