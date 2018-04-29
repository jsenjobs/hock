package com.jsen.test.controller.hc;

import com.jsen.test.service.HcModelService;
import com.jsen.test.service.TokenService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/16
 */
@RestController
@CrossOrigin
@RequestMapping("/hcModel")
public class HcModelController {
    @Autowired
    private HcModelService hcModelService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/model/create")
    public ResponseBase createModel(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("intro") String intro, @RequestParam("modelData") String modelData) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelService.create(name, intro, modelData, id);
    }

    @PostMapping("/model/update")
    public ResponseBase updateModel(@RequestParam("id") Integer modelId, @RequestParam("name") String name, @RequestParam("intro") String intro, @RequestParam("modelData") String modelData) {
        return hcModelService.updateById(modelId, name, intro, modelData);
    }

    @GetMapping("/model/reset")
    public ResponseBase reset(HttpServletRequest request) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelService.clearAllDataByUserId(id);
    }

    @GetMapping("/model/find")
    public ResponseBase createModel(HttpServletRequest request) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelService.findByUserId(id);
    }

    @GetMapping("/model/updateName/{oldModelName}/{newModelName}")
    public ResponseBase updateModelName(@PathVariable("oldModelName") String oldModelName, @PathVariable("newModelName") String newModelName) {
        return hcModelService.updateModelNameByName(oldModelName, newModelName);
    }

}
