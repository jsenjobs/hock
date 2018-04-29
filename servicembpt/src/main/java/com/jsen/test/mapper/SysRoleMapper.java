package com.jsen.test.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jsen.test.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {
    int insertRole(SysRole sysRole);

    int deleteRole(String value);

    List<SysRole> listPage(Pagination pagination);

    int countRole();

    SysRole getRoleByValue(String value);

    SysRole getRoleById(int id);

    List<SysRole> listAll();

}
