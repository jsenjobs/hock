package com.jsen.test.jobhandler.quartz.dy

import com.jsen.test.jobhandler.quartz.dy.sk.IJob
import com.jsen.test.service.AccountService
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 * 这个类可以直接远程执行
 * 其他还可以编写groovy代码（继承自IJob）进行执行
 */
class DynamicJob extends IJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicJob.class)

    @Autowired
    AccountService accountService;


    @Override
    void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info(accountService.listAccountAll().toJSONString())
        LOGGER.info("Dynamic Job执行时间: " + new Date())
    }
}
