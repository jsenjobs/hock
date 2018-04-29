package com.jsen.test.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2018-03-30
 */
@Data
@Accessors(chain = true)
public class Quartzjob extends Model<Quartzjob> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private String jobClazz;
    private String cron;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
