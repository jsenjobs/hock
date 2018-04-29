package com.jsen.test.service;

import com.jsen.test.entity.SysRole;
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
public interface SysRoleService extends IService<SysRole> {

    ResponseBase createRole(String value);
    ResponseBase deleteRole(String value);
    ResponseBase listRole(int page, int capacity);

    ResponseBase listAll();

}
