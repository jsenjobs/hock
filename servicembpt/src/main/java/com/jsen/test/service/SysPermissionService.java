package com.jsen.test.service;

import com.jsen.test.entity.SysPermission;
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
public interface SysPermissionService extends IService<SysPermission> {
    ResponseBase createPermission(String permission);
    ResponseBase deletePermission(String permission);
    ResponseBase listPermission(int page, int capacity);

    ResponseBase listAll();
}
