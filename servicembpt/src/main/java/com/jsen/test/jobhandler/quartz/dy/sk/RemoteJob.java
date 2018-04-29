package com.jsen.test.jobhandler.quartz.dy.sk;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
public class RemoteJob implements Job {
    IJob iJob = null;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (iJob == null) {
            iJob = (IJob)context.getJobDetail().getJobDataMap().get("ijob");
        }
        if (iJob != null) {
            iJob.execute(context);
        }
    }
}
