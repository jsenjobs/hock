package com.jsen.test.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jsen.test.entity.SysPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysUser;
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
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    int insertPermission(SysPermission sysPermission);

    int deletePermission(String permission);

    List<SysPermission> listPage(Pagination pagination);

    int countPermission();

    SysPermission getPermissionByPermission(String permission);

    SysPermission getPermissionById(int id);

    List<SysPermission> listAll();
}
