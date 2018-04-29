package com.jsen.test.mapper;

import com.jsen.test.entity.DBMetaColumn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/14
 */
@Service
public interface DBMetaMapper {
    List<DBMetaColumn> listColumnForTable(@Param("tableName") String tableName);

    List listTablesForDB(@Param("dbName") String dbName);
}
