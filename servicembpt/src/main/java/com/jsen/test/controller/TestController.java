package com.jsen.test.controller;

import com.jsen.test.entity.Account;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    @Autowired
    TestService service;
    @RequestMapping("/test001")
    public List<Account> test() {
        return service.getAllUser();
    }
    @RequestMapping("/test002")
    public Map<String, Integer> test2() {
        return service.TestAll();
    }

    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> excTest(RuntimeException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", e.getMessage());
        return result;
    }
}
