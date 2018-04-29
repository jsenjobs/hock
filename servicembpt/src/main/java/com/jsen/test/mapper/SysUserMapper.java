package com.jsen.test.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jsen.test.entity.SysPermission;
import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.utils.ResponseBase;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysRole> getRoleByUserId(int id);
    List<SysPermission> getPermissionByRoleId(int id);

    int insertUser(SysUser sysUser);
    int deleteUser(String name);

    List<SysUser> listPage(Pagination pagination);

    int countUser();

    SysUser getUserByName(String name);
    SysUser getUserById(int id);

}
