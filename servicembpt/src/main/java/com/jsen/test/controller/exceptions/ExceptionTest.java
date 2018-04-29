package com.jsen.test.controller.exceptions;

import com.jsen.test.utils.ResponseBase;
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
@RequestMapping("/exception")
public class ExceptionTest {

    @RequestMapping("/inner/{thr}")
    public ResponseBase InnerThrow(@PathVariable("thr") boolean thr) {
        if (thr) {
            throw new InnerException("InnerThrow");
        }
        return ResponseBase.create().code(0).msg("InnerThrow");
    }



    @RequestMapping("/union/{thr}")
    public ResponseBase UnionThrow(@PathVariable("thr") boolean thr) {
        if (thr) {
            throw new OutException("UnionThrow");
        }
        return ResponseBase.create().code(0).msg("UnionThrow");
    }

    @ExceptionHandler(InnerException.class)
    public ResponseBase innerHandler(InnerException innerException) {
        return ResponseBase.create().code(1).msg("innerHandler").add("e", innerException.getMessage());

    }
}
