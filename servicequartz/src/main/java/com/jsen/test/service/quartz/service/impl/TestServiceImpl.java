package com.jsen.test.service.quartz.service.impl;

import com.google.common.collect.Lists;
import com.jsen.test.service.quartz.model.TestUser;
import com.jsen.test.service.quartz.service.TestService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/28
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public ResponseBase listUsers() {
        List<TestUser> userList = Lists.newArrayList();
        userList.add(new TestUser().setId(1).setName("jsen").setSex(0));
        userList.add(new TestUser().setId(2).setName("jack").setSex(0));
        userList.add(new TestUser().setId(3).setName("lucy").setSex(1));
        userList.add(new TestUser().setId(4).setName("echo").setSex(1));
        userList.add(new TestUser().setId(5).setName("john").setSex(0));
        return ResponseBase.create().code(0).data(userList);
    }
}
