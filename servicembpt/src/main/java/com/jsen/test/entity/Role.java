package com.jsen.test.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@Data
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("Role")
    private Integer Role;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
