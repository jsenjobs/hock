package com.jsen.test.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;


/**
 * <p>
 *
 * </p>
 *
 * @author jsen
 * @since 2018-03-28
 */
@Data
@Accessors(chain = true)
public class Person {
    @NotNull(message="姓名不能为空")
    private String name;
    //private String sex;
    //private int age;

    @AssertFalse(message = "dead必须为False")
    private boolean dead;

    @Length(min=6,message="密码长度不能小于6位")
    @NotNull(message="密码不能为空")
    private String password;
    //private String birthday;
}
