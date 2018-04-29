package com.jsen.test.service;

import com.jsen.test.utils.ResponseBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/27
 */
@FeignClient("service-quartz")
@RequestMapping("/dom")
@Service
public interface EurekaTestService {
    @GetMapping("/echo/{name}")
    String echo(@PathVariable("name") String name);

    @GetMapping("/echoBody/{name}")
    ResponseBase echoBody(@PathVariable("name") String name);
}
