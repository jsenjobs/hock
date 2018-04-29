package com.jsen.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/14
 */
@Data
@Accessors(chain = true)
public class DBMetaColumn {
    // 列名
    private String field;
    // 列的类型
    private String type;

    // YES NO
    private String _null;

    // PRI _
    private String key;

    private String _default;

    private String extra;
}
