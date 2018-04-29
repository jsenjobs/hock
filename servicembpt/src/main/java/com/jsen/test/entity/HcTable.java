package com.jsen.test.entity;

import com.baomidou.mybatisplus.activerecord.Model;
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
public class HcTable extends Model<HcTable> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String tableName;
    private String metaName;
    private String comment;
    private String tag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
