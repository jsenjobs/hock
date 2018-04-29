package com.jsen.test.controller;

import com.jsen.test.utils.ResponseBase;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/8
 */
@RestController
@CrossOrigin
public class CommonExeController {
    @GetMapping("/401")
    public ResponseBase exec_401() {
        return ResponseBase.create().code(1).hcode(401).msg("Unauthorized");
    }
    @GetMapping("/401/{message}")
    public ResponseBase exec_401_msg(@PathVariable("message") String message) {
        return ResponseBase.create().code(1).hcode(401).msg(message);
    }
    @GetMapping("/pub/loginFailed")
    public ResponseBase loginFailed() {
        return ResponseBase.create().code(1).hcode(401).msg("登入失败");
    }

}
