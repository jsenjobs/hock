package com.jsen.test.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/19
 */
@Service
public interface DynamicPreCoreMapper {
    /**
     * 执行 select sql语句
     * 安全性由java负责
     * */
    List list(@Param("sqlStr") String sql);

    /**
     * 执行sql 无返回值， 一般可用于创建视图
     * @param sql
     * @return
     */
    String  execSqlReturnView(@Param("sqlStr") String sql);

    String testView();

    String testDeleteView();
}
