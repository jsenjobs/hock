package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysUser;
import com.jsen.test.mapper.SysRoleMapper;
import com.jsen.test.mapper.SysUserMapper;
import com.jsen.test.mapper.SysUserRoleMapper;
import com.jsen.test.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.service.TokenService;
import com.jsen.test.utils.MD5Util;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserRoleMapper userRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    TokenService tokenService;
    // 4 hour
    public static long shortExp = 60 * 60 * 4;
    public static long LongExp = 60 * 60 * 24 * 7;

    @Override
    public ResponseBase createUser(String name) {
        SysUser sysUser = baseMapper.getUserByName(name);
        if (sysUser != null) {
            return ResponseBase.create().code(1).msg("用户存在");
        }
        sysUser = new SysUser().setName(name).setPassword("123456");
        int eff = baseMapper.insertUser(sysUser);
        sysUser = baseMapper.getUserByName(name);
        return ResponseBase.create().code(0).add("eff", eff).add("data", sysUser);
    }

    @Override
    public ResponseBase deleteUser(String name) {
        SysUser sysUser = baseMapper.getUserByName(name);
        if (sysUser != null) {
            int eff = userRoleMapper.deleteUserRoleByUId(sysUser.getId());
            return ResponseBase.create().code(0).add("eff", baseMapper.deleteUser(name)).add("user_role_eff", eff);
        } else {
            return ResponseBase.create().code(1).msg("user not exist");
        }
    }

    @Override
    public ResponseBase listUser(int page, int capacity) {
        // user with roles
        return ResponseBase.create().code(0).add("data", baseMapper.listPage(new Page<SysUser>(page, capacity))).add("total", count());
    }

    @Override
    public ResponseBase login(String username, String password) {
        SysUser sysUser = baseMapper.getUserByName(username);

        try {
            if (!MD5Util.verify(password, sysUser.getPassword())) {
                return ResponseBase.create().code(1).msg("密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg("加密出错");
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", sysUser.getId());
        jsonObject.put("username", sysUser.getName());
        jsonObject.put("nickname", sysUser.getName());
        try {
            String tk = tokenService.genToken(jsonObject, sysUser.getPassword(), shortExp);
            String rTk = tokenService.genToken(jsonObject, sysUser.getPassword(), LongExp);
            return ResponseBase.create().code(0).add("token", tk).add("rToken", rTk).add("username", sysUser.getName()).add("id", sysUser.getId()).add("sex", sysUser.getSex());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg("获取token失败");
        }
    }

    private int count() {
        return baseMapper.countUser();
    }
}
