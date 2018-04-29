package com.jsen.test.service.quartz.controller;

import com.jsen.test.service.quartz.service.TestService;
import com.jsen.test.utils.ResponseBase;
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
public class TestContractController {

    @Autowired
    TestService testService;


    @GetMapping(value = "/fraudcheck",
            consumes="application/json",
            produces="application/json")
    public ResponseBase echo(@RequestParam(value = "id", required = false) Integer id,
                             @RequestParam(value = "loanAmount", required = false) Integer loanAmount) {
        System.err.println(id);
        System.err.println(loanAmount);
        return ResponseBase.create().code(0).add("fraudCheckStatus", "FRAUD").add("rejection.reason", "Amount too high");

    }
}
