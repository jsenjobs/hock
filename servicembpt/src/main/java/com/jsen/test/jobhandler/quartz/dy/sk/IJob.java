package com.jsen.test.jobhandler.quartz.dy.sk;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
public abstract class IJob {
    public abstract void execute(JobExecutionContext context) throws JobExecutionException;
}
