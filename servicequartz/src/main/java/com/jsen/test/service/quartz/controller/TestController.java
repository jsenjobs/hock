package com.jsen.test.service.quartz.controller;

import com.jsen.test.service.quartz.service.TestService;
import com.jsen.test.utils.ResponseBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/3
 */
@RestController
@CrossOrigin
@RequestMapping("/dom")
public class TestController {

    @Autowired
    TestService testService;


    @GetMapping("/echo/{name}")
    @HystrixCommand(fallbackMethod = "echoFailed") // 服务调用失败后执行的
    public String echo(@PathVariable("name") String name) {
        System.out.println("echo");
        return "hello " + name;
    }

    @GetMapping("/echoBody/{name}")
    @HystrixCommand(fallbackMethod = "echoFailedBody") // 服务调用失败后执行的
    public ResponseBase echoBody(@PathVariable("name") String name) {
        return ResponseBase.create().code(0).add("data", "hello " + name);
    }

    public String echoFailed(String name) {
        return "failed echo to " + name;
    }

    public ResponseBase echoFailedBody(String name) {
        return ResponseBase.create().code(1).add("data", "failed echo to " + name);
    }

    @GetMapping("/listUser")
    public ResponseBase listUsers() {
        return testService.listUsers();
    }
}
