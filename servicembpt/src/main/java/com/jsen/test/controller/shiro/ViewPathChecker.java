package com.jsen.test.controller.shiro;

import com.jsen.test.utils.ResponseBase;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/10
 */
@RestController
@CrossOrigin
public class ViewPathChecker {

    @GetMapping("/ViewPath/**")
    public ResponseBase checker() {
        return ResponseBase.create().code(0);
    }
}
