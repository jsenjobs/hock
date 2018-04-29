package com.jsen.test.config.shiro;

import com.google.common.base.Throwables;
import com.google.common.collect.Sets;
import com.jsen.test.config.shiro.exception.TokenException;
import com.jsen.test.entity.Account;
import com.jsen.test.entity.SysPermission;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysUser;
import com.jsen.test.mapper.SysUserMapper;
import com.jsen.test.service.TokenService;
import com.jsen.test.service.impl.AccountServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    TokenService tokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo1");

        int id = tokenService.getUserId(principalCollection.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = Sets.newHashSet();
        for (SysRole role:sysUserMapper.getRoleByUserId(id)) {
            simpleAuthorizationInfo.addRole(role.getValue());
            // System.out.println(role.getValue());
            for (SysPermission permission:sysUserMapper.getPermissionByRoleId(role.getId())) {
                permissions.add(permission.getPermission());
                // System.out.println(permission.getPermission());
            }
        }
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        System.out.println("doGetAuthenticationInfo");

        String token = (String) authenticationToken.getCredentials();
        try {
            if (token == null) {
                // throw new AuthenticationException("token为空");
                return null;
            }
            int id = tokenService.getUserId(token);

            SysUser sysUser = sysUserMapper.getUserById(id);

            if (sysUser == null) {
                throw new TokenException("用户不存在");
            }

            tokenService.validToken(token, sysUser.getPassword(), AccountServiceImpl.shortExp);
            return new SimpleAuthenticationInfo(token, token, "my_realm");
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new AuthenticationException();
            //e.printStackTrace();
           //return null;
            // throw new TokenException(e.getMessage());
        }
        /*
        // 解密获得username，用于和数据库进行对比
        int id = tokenService.getUserId(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserBean userBean = userService.getUser(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
        */

    }
}
