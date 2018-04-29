package com.jsen.test.mapper;

import com.jsen.test.entity.SysPermission;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysRolePermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
@Service
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    int insertRolePermission(SysRolePermission sysRolePermission);

    int deleteRolePermissionByRoleIdAndPermissionId(int roleId, int permissionId);

    int deleteRolePermissionByRId(int id);
    int deleteRolePermissionByPId(int id);

    List<SysPermission> listPermissionByRId(int id);

    SysRolePermission getPermissionByRoleIdAndPermissionId(int role_id, int permission_id);
}
