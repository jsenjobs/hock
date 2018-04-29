package com.jsen.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/14
 */
@Data
@Accessors(chain = true)
public class DBMetaDatabase {
    // 类别名称；它必须与存储在数据库中的类别名称匹配；该参数为 "" 表示获取没有类别的那些描述；为 null 则表示该类别名称不应该用于缩小搜索范围
    private final String catalog;
    // schemaPattern - 模式名称的模式；它必须与存储在数据库中的模式名称匹配；该参数为 "" 表示获取没有模式的那些描述；为 null
    // 则表示该模式名称不应该用于缩小搜索范围
    private final String schema;
    // 表格的集合
    private final Map<String, DBMetaTable> tables = new HashMap<String, DBMetaTable>();
}
