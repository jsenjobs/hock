package com.jsen.test.service.quartz.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
@Data
@Accessors(chain = true)
public class JobConf {
    @NotEmpty
    private String jobName;
    @NotEmpty
    private String jobGroup;
    @NotEmpty
    private String triggerName;
    @NotEmpty
    private String triggerGroup;
    private String sourceData;
    @NotEmpty
    private String cron;
    @NotEmpty
    private String serverName;
}
