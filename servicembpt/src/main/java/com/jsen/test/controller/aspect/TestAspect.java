package com.jsen.test.controller.aspect;

import com.jsen.test.exceptions.TestException;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/2
 */
@RestController
@CrossOrigin
@RequestMapping("/aspect")
public class TestAspect {

    @Autowired
    Test002 test002;

    @RequestMapping("/common")
    public ResponseBase common() {
        testScope();
        // test002.test();
        return ResponseBase.create().code(0).msg("common");
    }

    @RequestMapping("/exception")
    public ResponseBase exceptionE() {
        if (true) {
            throw new TestException("run error");
        }
        return ResponseBase.create().code(1).msg("exception");
    }

    @RequestMapping("/testScope")
    public ResponseBase testScope() {
        System.out.println("inner method");
        return ResponseBase.create().code(0).msg("common");
    }

    @ExceptionHandler(TestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBase rE(TestException e) {
        return ResponseBase.create().code(1).msg(e.getMessage());
    }

}
