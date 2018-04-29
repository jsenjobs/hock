package com.jsen.test.controller.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jsen.test.utils.ResponseBase;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/16
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class ShiroCommon {
    private static final String[] as = new String[] {};
    private static final ResponseBase OK = ResponseBase.create().code(0);
    private static final ResponseBase NO_OK= ResponseBase.create().code(1);
    @GetMapping("/has/role/{role}")
    public ResponseBase hasRole(@PathVariable("role") String role) {
        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole(role) ? OK : NO_OK;
    }
    @GetMapping("/has/permission/{permission}")
    public ResponseBase hasPermission(@PathVariable("permission") String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(permission) ? OK : NO_OK;
    }
    @GetMapping("/has/permissions/{permissions}")
    public ResponseBase hasPermissions(@PathVariable("permissions") String permissions) {
        try {
            JSONArray jsonArray = JSON.parseArray(permissions);
            Subject subject = SecurityUtils.getSubject();
            return subject.isPermittedAll(jsonArray.toArray(as)) ? OK : NO_OK;
        } catch (Exception e) {
            return NO_OK;
        }
    }
}
