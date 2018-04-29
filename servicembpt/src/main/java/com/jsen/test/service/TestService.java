package com.jsen.test.service;

import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.entity.Account;
import com.jsen.test.entity.Role;

import java.util.List;
import java.util.Map;

public interface TestService extends IService<Account> {
    List<Account> getAllUser();


    Map<String, Integer> TestAll();
}
