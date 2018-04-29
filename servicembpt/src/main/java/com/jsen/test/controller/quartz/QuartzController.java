package com.jsen.test.controller.quartz;

import com.jsen.test.entity.Quartzjob;
import com.jsen.test.service.QuartzjobService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/3/30
 */
@RestController
@CrossOrigin
@RequestMapping("/quartz")
public class QuartzController {
    static String name = "package com.jsen.test.jobhandler.quartz.dy\n" +
            "\n" +
            "import org.quartz.JobExecutionContext\n" +
            "import org.quartz.JobExecutionException\n" +
            "import org.slf4j.Logger\n" +
            "import org.slf4j.LoggerFactory\n" +
            "\n" +
            "/**\n" +
            " * <p>\n" +
            " * </p>\n" +
            " *\n" +
            " * @author ${User}\n" +
            " * @since 2018/4/4\n" +
            " */\n" +
            "class DynamicJob implements BaseJob {\n" +
            "    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicJob.class)\n" +
            "\n" +
            "    @Override\n" +
            "    void execute(JobExecutionContext context) throws JobExecutionException {\n" +
            "        LOGGER.error(\"Dynamic Job执行时间: \" + new Date())\n" +
            "    }\n" +
            "}\n";
    @Autowired
    QuartzjobService quartzjobService;
    @GetMapping("/create")
    public ResponseBase create(Quartzjob quartzjob) {
        return quartzjobService.addJob(quartzjob);
    }
    @GetMapping("/update")
    public ResponseBase update(Quartzjob quartzjob) {
        return quartzjobService.updateJob(quartzjob);

    }
    @GetMapping("/remove")
    public ResponseBase remove(Quartzjob quartzjob) {
        return quartzjobService.removeJob(quartzjob);

    }
    @GetMapping("/pause")
    public ResponseBase pause(Quartzjob quartzjob) {
        return quartzjobService.pauseJob(quartzjob);

    }
    @GetMapping("/resume")
    public ResponseBase resume(Quartzjob quartzjob) {
        return quartzjobService.resumeJob(quartzjob);

    }
    @GetMapping("/resumeall")
    public ResponseBase resumeAll() {
        return quartzjobService.resumeAllJob();

    }
    @GetMapping("/pauseall")
    public ResponseBase pauseAll() {
        return quartzjobService.pauseAllJob();
    }
    @GetMapping("/shutdown")
    public ResponseBase shutdown() {
        return quartzjobService.shutdown();
    }
}
