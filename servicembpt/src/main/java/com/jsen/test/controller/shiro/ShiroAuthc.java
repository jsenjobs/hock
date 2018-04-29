package com.jsen.test.controller.shiro;

import com.jsen.test.utils.ResponseBase;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/shiro")
public class ShiroAuthc {
    @GetMapping("/public")
    public ResponseBase pub() {
        return ResponseBase.create().code(0).msg("公共资源");
    }
    @GetMapping("/admin")
    // @RequiresPermissions("userInfo:add")//权限管理;
    @RequiresRoles("admin")
    public ResponseBase admin() {
        return ResponseBase.create().code(0).msg("admin角色");
    }
}
