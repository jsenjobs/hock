package com.jsen.test.service.quartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.service.quartz.model.JobConf;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/4
 */
@RestController
@CrossOrigin
@RequestMapping("/job")
public class DyQuartzClient {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/create")
    public ResponseBase createJob(JobConf jobConf) {
        System.out.println("http://" + jobConf.getServerName() + "/eureka/quartz/create?data=" + JSONObject.toJSON(jobConf).toString());
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/create?data=" + JSONObject.toJSON(jobConf).toString(), ResponseBase.class);
    }
    @GetMapping("/update")
    public ResponseBase updateJob(JobConf jobConf) {
        System.out.println("http://" + jobConf.getServerName() + "/eureka/quartz/update?data=" + JSONObject.toJSON(jobConf).toString());
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/update?data=" + JSONObject.toJSON(jobConf).toString(), ResponseBase.class);
    }
    @GetMapping("/resume")
    public ResponseBase resumeJob(JobConf jobConf) {
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/resume?data=" + JSONObject.toJSON(jobConf).toString(), ResponseBase.class);
    }
    @GetMapping("/pause")
    public ResponseBase pauseJob(JobConf jobConf) {
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/pause?data=" + JSONObject.toJSON(jobConf).toString(), ResponseBase.class);
    }
    @GetMapping("/resumeAll")
    public ResponseBase resumeAllJob(JobConf jobConf) {
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/resumeAll", ResponseBase.class);
    }
    @GetMapping("/pauseAll")
    public ResponseBase pauseAllJob(JobConf jobConf) {
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/pauseAll", ResponseBase.class);
    }
    @GetMapping("/shutdown")
    public ResponseBase pshutdownJob(JobConf jobConf) {
        return restTemplate.getForObject("http://" + jobConf.getServerName() + "/eureka/quartz/shutdown", ResponseBase.class);
    }
}
