package com.jsen.test.controller;


import com.alibaba.fastjson.JSONObject;
import com.jsen.test.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
@RestController
@RequestMapping("/account")
@CrossOrigin
@Api("Account类描述")
public class AccountController {

    @Autowired
    AccountService accountService;
    // api rest

    @GetMapping("/login/{domain}/{token}")
    @ApiOperation("Api方法描述")
    public JSONObject login(@ApiParam("Api参数描述") @PathVariable("domain") String domain, @PathVariable("token") String token) {
        // need a token
        return _login(domain, token);
    }

    @GetMapping("/create/{domain}/{password}/{sex}")
    public JSONObject create(@PathVariable("domain") String domain, @PathVariable("password") String password, @PathVariable("sex") String sex) {
        return accountService.create(domain, password, sex);
    }

    // requestParam
    @PostMapping("/post/login")
    public JSONObject login1(@RequestParam("domain") String domain, @RequestParam("token") String token) {
        return _login(domain, token);
    }
    @PostMapping("/postbody/login")
    public JSONObject login2(@RequestBody Map<String, Object> data) {
        String domain = data.get("domain").toString();
        String token = data.get("token").toString();
        return _login(domain, token);
    }


    // @RequestHeader //@CookieVal

    // logical
    private JSONObject _login(String domain, String token) {
        return  accountService.login(domain, token);
    }



}

