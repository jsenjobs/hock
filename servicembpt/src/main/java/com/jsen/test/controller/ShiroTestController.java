package com.jsen.test.controller;


import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.ResponseBase;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/access")
public class ShiroTestController {

    @RequestMapping("/public")
    public JSONObject _public() {
        return ResponseBase.create().code(0).msg("public resources");
    }

    @RequestMapping("/online")
    // @RequiresAuthentication
    public JSONObject _online() {
        return ResponseBase.create().code(0).msg("online resources");
    }

}
