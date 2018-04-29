package com.jsen.test.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
@Data
@Accessors(chain = true)
public class Testtable extends Model<Testtable> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String sex;
    private Integer testint;
    private Float testnumber;
    private Date time;
    private Integer logical;
    private String testchar;
    private Integer tenantid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
