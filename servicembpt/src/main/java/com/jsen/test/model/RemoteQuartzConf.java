package com.jsen.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
@Data
@Accessors(chain = true)
public class RemoteQuartzConf {
    private Integer id;
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private String sourceData;
    private String cron;
}
