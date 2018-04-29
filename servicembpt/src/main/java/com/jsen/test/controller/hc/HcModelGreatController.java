package com.jsen.test.controller.hc;

import com.jsen.test.service.HcModelGreatService;
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
 * @since 2018/4/26
 */
@RestController
@CrossOrigin
@RequestMapping("/hcModel/great")
public class HcModelGreatController {
    @Autowired
    HcModelGreatService hcModelGreatService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/great/{mId}")
    public ResponseBase great(HttpServletRequest request, @PathVariable("mId") Integer mId) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelGreatService.great(id, mId);
    }
}
