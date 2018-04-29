package com.jsen.test.service;

import com.jsen.test.model.RemoteQuartzConf;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
public interface RemoteQuartzService {
    ResponseBase addJob(RemoteQuartzConf remoteQuartzConf) throws Exception;
    ResponseBase updateJob(RemoteQuartzConf remoteQuartzConf);
    ResponseBase removeJob(RemoteQuartzConf remoteQuartzConf);

    ResponseBase pauseJob(RemoteQuartzConf remoteQuartzConf);
    ResponseBase resumeJob(RemoteQuartzConf remoteQuartzConf);

    ResponseBase resumeAllJob();
    ResponseBase pauseAllJob();
    ResponseBase shutdown();
}
