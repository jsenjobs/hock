package com.jsen.test.service.impl;

import com.jsen.test.entity.Quartzjob;
import com.jsen.test.jobhandler.quartz.dy.sk.IJob;
import com.jsen.test.mapper.QuartzjobMapper;
import com.jsen.test.service.QuartzjobService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-30
 */
@Service
public class QuartzjobServiceImpl extends ServiceImpl<QuartzjobMapper, Quartzjob> implements QuartzjobService {
    private static final Logger logger = LoggerFactory.getLogger(QuartzjobServiceImpl.class);
    @Autowired
    private Scheduler scheduler;

    @Override
    public ResponseBase addJob(Quartzjob quartzjob) {
        try {
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(quartzjob.getJobClazz())).withIdentity(quartzjob.getJobName(), quartzjob.getJobGroup()).build();


            TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
            triggerTriggerBuilder.startNow();
            triggerTriggerBuilder.withIdentity(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            // 触发器时间
            triggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzjob.getCron()));
            Trigger trigger = triggerTriggerBuilder.build();

            scheduler.scheduleJob(jobDetail, trigger);


            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (ClassNotFoundException | SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase addJob(Quartzjob quartzjob, IJob iJob) {
        try {

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("ijob", iJob);
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(quartzjob.getJobClazz())).withIdentity(quartzjob.getJobName(), quartzjob.getJobGroup())
                    .setJobData(jobDataMap).build();

            TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
            triggerTriggerBuilder.startNow();
            triggerTriggerBuilder.withIdentity(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            // 触发器时间
            triggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzjob.getCron()));
            Trigger trigger = triggerTriggerBuilder.build();

            scheduler.scheduleJob(jobDetail, trigger);


            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (ClassNotFoundException | SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase updateJob(Quartzjob quartzjob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (trigger == null) {
                return ResponseBase.create().code(1).msg("trigger with key:" + quartzjob.getTriggerName() + quartzjob.getTriggerGroup() + " is not exist.");
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(quartzjob.getCron())) {
                TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
                triggerTriggerBuilder.startNow();
                triggerTriggerBuilder.withIdentity(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
                // 触发器时间
                triggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzjob.getCron()));
                trigger = (CronTrigger)triggerTriggerBuilder.build();

                scheduler.rescheduleJob(triggerKey, trigger);

                // way 2 delete add
                // removeJob(quartzjob);
                // addJob(quartzjob);

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase removeJob(Quartzjob quartzjob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(quartzjob.getJobName(), quartzjob.getJobGroup()));

        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase pauseJob(Quartzjob quartzjob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            scheduler.pauseTrigger(triggerKey);
            scheduler.pauseJob(JobKey.jobKey(quartzjob.getJobName(), quartzjob.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase resumeJob(Quartzjob quartzjob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            scheduler.resumeTrigger(triggerKey);
            scheduler.resumeJob(JobKey.jobKey(quartzjob.getJobName(), quartzjob.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase resumeAllJob() {
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase pauseAllJob() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase shutdown() {
        // shutdown后无法再使用
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

}
