package com.jsen.test.jobhandler.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/3/30
 */
@EnableScheduling
@Component // 此注解必加
public class SimpleTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTask.class);
    public void run() {
        LOGGER.error("SimpleTask执行时间: " + new Date());

    }
}
