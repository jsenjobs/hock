package com.jsen.test.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class HcTheme extends Model<HcTheme> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer parent;
    private String comment;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
