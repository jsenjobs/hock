package com.jsen.test.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/19
 */
@Service
public interface DynamicCoreMapper {
    /**
     * 通过视图名字删除视图
     * @param name 视图名字
     * @return
     */
    String delViewTableByName(@Param("viewTableName") String name);
}
