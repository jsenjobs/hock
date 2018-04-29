package com.jsen.test.service.impl;

import com.jsen.test.entity.Quartzjob;
import com.jsen.test.jobhandler.quartz.dy.sk.IJob;
import com.jsen.test.jobhandler.quartz.dy.sk.RemoteJob;
import com.jsen.test.model.RemoteQuartzConf;
import com.jsen.test.service.QuartzjobService;
import com.jsen.test.service.RemoteQuartzService;
import com.jsen.test.utils.ResponseBase;
import com.xxl.job.core.executor.XxlJobExecutor;
import groovy.lang.GroovyClassLoader;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
@Service
public class RemoteQuartzServiceImpl implements RemoteQuartzService {
    private static final Logger logger = LoggerFactory.getLogger(RemoteQuartzServiceImpl.class);

    private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
    @Autowired
    QuartzjobService quartzjobService;
    @Override
    public ResponseBase addJob(RemoteQuartzConf remoteQuartzConf) throws Exception {

        Class<?> clazz = groovyClassLoader.parseClass(remoteQuartzConf.getSourceData());
        if (clazz != null) {
            Object instance = clazz.newInstance();
            if (instance != null && instance instanceof IJob) {
                injectService(instance);
                Quartzjob quartzjob = new Quartzjob().setCron(remoteQuartzConf.getCron())
                        .setJobName(remoteQuartzConf.getJobName()).setJobGroup(remoteQuartzConf.getJobGroup())
                        .setTriggerName(remoteQuartzConf.getTriggerName()).setTriggerGroup(remoteQuartzConf.getTriggerGroup())
                        .setJobClazz(RemoteJob.class.getName());
                return quartzjobService.addJob(quartzjob, (IJob)instance);
            } else {
                return ResponseBase.create().code(1).msg("类必须继承自IJob");
            }
        }
        return ResponseBase.create().code(1).msg("groovy 编译出错");
    }

    @Override
    public ResponseBase updateJob(RemoteQuartzConf remoteQuartzConf) {
        Quartzjob quartzjob = new Quartzjob().setCron(remoteQuartzConf.getCron())
                .setJobName(remoteQuartzConf.getJobName()).setJobGroup(remoteQuartzConf.getJobGroup())
                .setTriggerName(remoteQuartzConf.getTriggerName()).setTriggerGroup(remoteQuartzConf.getTriggerGroup());

        return quartzjobService.updateJob(quartzjob);
    }

    @Override
    public ResponseBase removeJob(RemoteQuartzConf remoteQuartzConf) {
        Quartzjob quartzjob = new Quartzjob()
                .setJobName(remoteQuartzConf.getJobName()).setJobGroup(remoteQuartzConf.getJobGroup())
                .setTriggerName(remoteQuartzConf.getTriggerName()).setTriggerGroup(remoteQuartzConf.getTriggerGroup());

        return quartzjobService.removeJob(quartzjob);
    }

    @Override
    public ResponseBase pauseJob(RemoteQuartzConf remoteQuartzConf) {
        Quartzjob quartzjob = new Quartzjob()
                .setJobName(remoteQuartzConf.getJobName()).setJobGroup(remoteQuartzConf.getJobGroup())
                .setTriggerName(remoteQuartzConf.getTriggerName()).setTriggerGroup(remoteQuartzConf.getTriggerGroup());

        return quartzjobService.pauseJob(quartzjob);
    }

    @Override
    public ResponseBase resumeJob(RemoteQuartzConf remoteQuartzConf) {
        Quartzjob quartzjob = new Quartzjob()
                .setJobName(remoteQuartzConf.getJobName()).setJobGroup(remoteQuartzConf.getJobGroup())
                .setTriggerName(remoteQuartzConf.getTriggerName()).setTriggerGroup(remoteQuartzConf.getTriggerGroup());

        return quartzjobService.resumeJob(quartzjob);
    }

    @Override
    public ResponseBase resumeAllJob() {
        return quartzjobService.resumeAllJob();
    }

    @Override
    public ResponseBase pauseAllJob() {
        return quartzjobService.pauseAllJob();
    }

    @Override
    public ResponseBase shutdown() {
        return quartzjobService.shutdown();
    }




    /**
     * inject action of spring
     * @param instance
     */
    private void injectService(Object instance){
        if (instance==null) {
            return;
        }

        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Object fieldBean = null;
            // with bean-id, bean could be found by both @Resource and @Autowired, or bean could only be found by @Autowired
            if (AnnotationUtils.getAnnotation(field, Resource.class) != null) {
                try {
                    Resource resource = AnnotationUtils.getAnnotation(field, Resource.class);
                    if (resource.name()!=null && resource.name().length()>0){
                        fieldBean = XxlJobExecutor.getApplicationContext().getBean(resource.name());
                    } else {
                        fieldBean = XxlJobExecutor.getApplicationContext().getBean(field.getName());
                    }
                } catch (Exception e) {
                }
                if (fieldBean==null ) {
                    fieldBean = XxlJobExecutor.getApplicationContext().getBean(field.getType());
                }
            } else if (AnnotationUtils.getAnnotation(field, Autowired.class) != null) {
                Qualifier qualifier = AnnotationUtils.getAnnotation(field, Qualifier.class);
                if (qualifier!=null && qualifier.value()!=null && qualifier.value().length()>0) {
                    fieldBean = XxlJobExecutor.getApplicationContext().getBean(qualifier.value());
                } else {
                    fieldBean = XxlJobExecutor.getApplicationContext().getBean(field.getType());
                }
            }

            if (fieldBean!=null) {
                field.setAccessible(true);
                try {
                    field.set(instance, fieldBean);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
