package com.jsen.test.service;

import com.jsen.test.entity.Testtable;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
public interface TesttableService extends IService<Testtable> {
    List<Testtable> selectMyAll();

    List<Testtable> selectPart();


    List<Testtable> testSQL1();

    List<Testtable> testSQL2();

    List<Testtable> listAll();

    Map<String, Object> delete(int id);

    List<Testtable> list();
}
