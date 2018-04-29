package com.jsen.test.controller;


import com.jsen.test.entity.Account;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    TestService service;
    @RequestMapping("/test001")
    public List<Account> test() {
        return service.getAllUser();
    }

}

