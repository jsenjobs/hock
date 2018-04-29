package com.jsen.test.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jsen.test.entity.SysPermission;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysUser;
import com.jsen.test.mapper.SysPermissionMapper;
import com.jsen.test.mapper.SysRolePermissionMapper;
import com.jsen.test.service.SysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public ResponseBase createPermission(String permission) {

        SysPermission sysPermission = baseMapper.getPermissionByPermission(permission);
        if (sysPermission != null) {
            return ResponseBase.create().code(1).msg("权限存在");
        }
        sysPermission = new SysPermission().setPermission(permission);
        int eff = baseMapper.insertPermission(sysPermission);
        sysPermission = baseMapper.getPermissionByPermission(permission);
        return ResponseBase.create().code(0).add("eff", eff).add("data", sysPermission);
    }

    @Override
    public ResponseBase deletePermission(String permission) {

        SysPermission sysPermission = baseMapper.getPermissionByPermission(permission);
        if (permission != null) {
            int eff = sysRolePermissionMapper.deleteRolePermissionByPId(sysPermission.getId());
            return ResponseBase.create().code(0).add("eff", baseMapper.deletePermission(permission)).add("role_permission_eff", eff);
        } else {
            return ResponseBase.create().code(1).msg("permission not exist");
        }
    }


    @Override
    public ResponseBase listPermission(int page, int capacity) {
        return ResponseBase.create().code(0).add("data", baseMapper.listPage(new Page<SysPermission>(page, capacity))).add("total", count());
    }

    @Override
    public ResponseBase listAll() {
        return ResponseBase.create().code(0).data(baseMapper.listAll());
    }

    private int count() {
        return baseMapper.countPermission();
    }
}
