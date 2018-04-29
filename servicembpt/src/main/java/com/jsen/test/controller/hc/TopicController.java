package com.jsen.test.controller.hc;

import com.jsen.test.service.HcThemeService;
import com.jsen.test.service.TokenService;
import com.jsen.test.utils.ResponseBase;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/11
 */
@RestController
@CrossOrigin
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private HcThemeService hcThemeService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/listTree")
    public ResponseBase listTopics(HttpServletRequest request, HttpServletResponse response) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        System.out.println(id);
        return hcThemeService.listAllAsTree(id);
    }

    @GetMapping("/listAll")
    public ResponseBase listALl(HttpServletRequest request, HttpServletResponse response) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcThemeService.listAll(id);
    }

    @GetMapping("/addTopic/{name}/{comment}")
    public ResponseBase addTopic(@PathVariable("name") String name, @PathVariable("comment") String comment) {
        return hcThemeService.addTopic(name, comment);
    }

    @GetMapping("/addSubTopic/{name}/{comment}/{parent}")
    public ResponseBase addSubTopic(@PathVariable("name") String name, @PathVariable("comment") String comment, @PathVariable("parent") int parent) {
        return hcThemeService.addSubTopic(name, comment, parent);
    }

    @GetMapping("/delTopic/{parentId}")
    public ResponseBase delTopic(@PathVariable("parentId") Integer parentId) {
        return hcThemeService.delTopic(parentId);
    }

    @GetMapping("/delSubTopic/{id}")
    public ResponseBase delSubTopic(@PathVariable("id") Integer id) {
        return hcThemeService.delSubTopic(id);
    }
}
