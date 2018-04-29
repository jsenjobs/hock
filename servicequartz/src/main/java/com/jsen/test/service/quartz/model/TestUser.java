package com.jsen.test.service.quartz.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/28
 */
@Data
@Accessors(chain = true)
public class TestUser {
    private Integer id;
    private String name;
    private Integer sex;

}
