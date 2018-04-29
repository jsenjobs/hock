package com.jsen.test.controller.cloud;

import com.jsen.test.service.EurekaTestService;
import com.jsen.test.utils.ResponseBase;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/3
 */
@RestController
@CrossOrigin
@RequestMapping("/cloud")
public class TestEureka {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EurekaTestService eurekaTestService;

    @GetMapping("/echo")
    public ResponseBase echo() {
        // ServiceInstance serviceInstance = loadBalancerClient.choose("service-1");
        // String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dom/echo/jjjc";


        String data = restTemplate.getForObject("http://service-quartz/dom/echo/jjc" , String.class);
        return ResponseBase.create().code(0).msg(data);
    }

    @GetMapping("/feign/echo/{name}")
    public String fEcho(@PathVariable("name") String name) {
        try {
            return eurekaTestService.echo(name);
        } catch (FeignException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/feign/echoBody/{name}")
    public ResponseBase fEchoBody(@PathVariable("name") String name) {
        return eurekaTestService.echoBody(name);
    }
}
