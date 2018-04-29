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
public class DBMetaTable {
    //表名
    private final String name;
}
