package com.jsen.test.controller.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.jsen.test.model.RemoteQuartzConf;
import com.jsen.test.service.RemoteQuartzService;
import com.jsen.test.utils.ResponseBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
@RestController
@CrossOrigin
@RequestMapping("/eureka/quartz")
public class RemoteQuartz {
    private static final Logger logger = LoggerFactory.getLogger(RemoteQuartz.class);
    @Autowired
    RemoteQuartzService quartzjobService;
    @GetMapping("/create")
    public ResponseBase create(@RequestParam("data") String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            String sourceData = URLDecoder.decode(jsonObject.getString("sourceData").replaceAll("\\+", "%2B"), "UTF-8");
            RemoteQuartzConf remoteQuartzConf = new RemoteQuartzConf().setSourceData(sourceData)
                    .setJobName(jsonObject.getString("jobName"))
                    .setJobGroup(jsonObject.getString("jobGroup"))
                    .setTriggerName(jsonObject.getString("triggerName"))
                    .setTriggerGroup(jsonObject.getString("triggerGroup"))
                    .setCron(jsonObject.getString("cron"));
            return quartzjobService.addJob(remoteQuartzConf);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            return ResponseBase.create().code(1).msg("参数错误").add("data", data);
        }
    }
    @GetMapping("/update")
    public ResponseBase update(@RequestParam("data") String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            RemoteQuartzConf remoteQuartzConf = new RemoteQuartzConf()
                    .setJobName(jsonObject.getString("jobName"))
                    .setJobGroup(jsonObject.getString("jobGroup"))
                    .setTriggerName(jsonObject.getString("triggerName"))
                    .setTriggerGroup(jsonObject.getString("triggerGroup"))
                    .setCron(jsonObject.getString("cron"));
            return quartzjobService.updateJob(remoteQuartzConf);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            return ResponseBase.create().code(1).msg("参数错误").add("data", data);
        }

    }
    @GetMapping("/remove")
    public ResponseBase remove(@RequestParam("data") String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            RemoteQuartzConf remoteQuartzConf = new RemoteQuartzConf()
                    .setTriggerName(jsonObject.getString("triggerName"))
                    .setTriggerGroup(jsonObject.getString("triggerGroup"));
            return quartzjobService.removeJob(remoteQuartzConf);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            return ResponseBase.create().code(1).msg("参数错误").add("data", data);
        }


    }
    @GetMapping("/pause")
    public ResponseBase pause(@RequestParam("data") String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            RemoteQuartzConf remoteQuartzConf = new RemoteQuartzConf()
                    .setTriggerName(jsonObject.getString("triggerName"))
                    .setTriggerGroup(jsonObject.getString("triggerGroup"));
            return quartzjobService.pauseJob(remoteQuartzConf);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            return ResponseBase.create().code(1).msg("参数错误").add("data", data);
        }

    }
    @GetMapping("/resume")
    public ResponseBase resume(@RequestParam("data") String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            RemoteQuartzConf remoteQuartzConf = new RemoteQuartzConf()
                    .setJobName(jsonObject.getString("jobName"))
                    .setJobGroup(jsonObject.getString("jobGroup"))
                    .setTriggerName(jsonObject.getString("triggerName"))
                    .setTriggerGroup(jsonObject.getString("triggerGroup"));
            return quartzjobService.resumeJob(remoteQuartzConf);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            return ResponseBase.create().code(1).msg("参数错误").add("data", data);
        }

    }
    @GetMapping("/resumeAll")
    public ResponseBase resumeAll() {
        return quartzjobService.resumeAllJob();

    }
    @GetMapping("/pauseAll")
    public ResponseBase pauseAll() {
        return quartzjobService.pauseAllJob();
    }
    @GetMapping("/shutdown")
    public ResponseBase shutdown() {
        return quartzjobService.shutdown();
    }
}
