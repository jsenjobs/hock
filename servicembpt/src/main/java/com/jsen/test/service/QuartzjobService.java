package com.jsen.test.service;

import com.jsen.test.entity.Quartzjob;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.jobhandler.quartz.dy.sk.IJob;
import com.jsen.test.utils.ResponseBase;
import org.quartz.JobDataMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-03-30
 */
public interface QuartzjobService extends IService<Quartzjob> {

    ResponseBase addJob(Quartzjob quartzjob);
    ResponseBase addJob(Quartzjob quartzjob, IJob iJob);
    ResponseBase updateJob(Quartzjob quartzjob);
    ResponseBase removeJob(Quartzjob quartzjob);

    ResponseBase pauseJob(Quartzjob quartzjob);
    ResponseBase resumeJob(Quartzjob quartzjob);

    ResponseBase resumeAllJob();
    ResponseBase pauseAllJob();
    ResponseBase shutdown();


}
