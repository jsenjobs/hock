package com.jsen.test.service;

import com.jsen.test.entity.SysUser;
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
public interface SysUserService extends IService<SysUser> {
    ResponseBase createUser(String name);
    ResponseBase deleteUser(String name);
    ResponseBase listUser(int page, int capacity);

    ResponseBase login(String username, String password);
}
