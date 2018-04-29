package com.jsen.test.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.entity.Account;
import com.jsen.test.mapper.AccountMapper;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class TestServiceImpl extends ServiceImpl<AccountMapper, Account> implements TestService {
    @Override
    public List<Account> getAllUser() {
        return baseMapper.selectList(new EntityWrapper<>());
    }
    @Override
    @Transactional
    public Map<String, Integer> TestAll() {
        Map<String, Integer> result = new HashMap<>();
        // ADD
        Account u = new Account();
        u.setName("jack");
        u.setSex("nv");
        result.put("insert", baseMapper.insert(u));

        // DELETE
        result.put("delete", baseMapper.delete(new EntityWrapper<Account>().eq("name", "jsen")));

        if (true)
            throw new RuntimeException("Test Exception");
        u = new Account();
        u.setId(5);
        u.setName("jacc");
        result.put("update", baseMapper.updateById(u));
        u.setName("lucy");
        result.put("update2", baseMapper.update(u, new EntityWrapper<Account>().eq("name", "jacc")));

        result.put("list", baseMapper.selectPage(new Page<Account>(1, 10), new EntityWrapper<Account>().eq("name", "lucy")).size());

        return result;
    }


}
