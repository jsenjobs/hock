package com.jsen.test.controller.hc;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.service.HcModelShareService;
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
 * @since 2018/4/23
 */
@RestController
@CrossOrigin
@RequestMapping("/hcModel/share")
public class HcModelShareController {
    @Autowired
    private HcModelShareService hcModelShareService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/model/save/{modelId}/{modelName}/{intro}/{type}")
    public ResponseBase save(HttpServletRequest request, @PathVariable("modelId") Integer modelId, @PathVariable("modelName") String modelName, @PathVariable("intro") String intro, @PathVariable("type") Integer type) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelShareService.save(modelId, id, modelName, intro, type);
    }

    @GetMapping("/model/update/{shareModelName}/{modelId}")
    public ResponseBase updateModel(HttpServletRequest request, @PathVariable("shareModelName") String shareModelName, @PathVariable("modelId") Integer modelId) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelShareService.updateModelBy(shareModelName, id, modelId);
    }


    /**
     * 显示公共模型和用户的私有模型，并返回用户的点赞列表
     * @param request
     * @return
     */
    @GetMapping("/model/list")
    public ResponseBase listAll(HttpServletRequest request) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelShareService.listAll(id);
    }

    @GetMapping("/model/delete/{shareModelId}")
    public ResponseBase deleteModel(@PathVariable("shareModelId") Integer shareModelId) {
        return hcModelShareService.deleteShareModelById(shareModelId);
    }

    /**
     *
     * @param id
     * @param dynamicParams 动态参数， 如果动态参数没有，则使用默认参数
     * @return
     */
    @GetMapping("/model/exec/{id}")
    public ResponseBase execShareModel(@PathVariable("id") Integer id, @RequestParam(value = "dynamicParams", required = false) String dynamicParams) {
        if (dynamicParams == null) {
            return hcModelShareService.execModel(id, new JSONObject());
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(dynamicParams);
            return hcModelShareService.execModel(id, jsonObject);
        } catch (Exception e) {
            return hcModelShareService.execModel(id, new JSONObject());
        }
    }

}
