package com.jsen.test.mapper;

import com.jsen.test.entity.SysRole;
import com.jsen.test.entity.SysUserRole;
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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    int insertUserRole(SysUserRole sysUserRole);

    int deleteUserRoleById(int id);
    int deleteUserRoleByUserIdAndRoleId(int user_id, int role_id);

    int deleteUserRoleByUId(int id);
    int deleteUserRoleByRId(int id);

    List<SysRole> listRolesByUId(int id);

    SysUserRole getRoleByUIdAndRId(int user_id, int role_id);
}
