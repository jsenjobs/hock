package com.jsen.test.service;

import com.alibaba.fastjson.JSONArray;
import com.jsen.test.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-04-08
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    ResponseBase createUserRole(int u_id, int r_id);
    ResponseBase createUserRoles(int u_id, JSONArray r_ids);
    ResponseBase deleteUserRole(int user_id, int role_id);
}
