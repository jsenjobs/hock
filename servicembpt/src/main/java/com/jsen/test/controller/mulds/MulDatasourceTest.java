package com.jsen.test.controller.mulds;

import com.jsen.test.service.AccountService;
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
 * @since 2018/4/2
 */
@RestController
@CrossOrigin
@RequestMapping("/mul")
public class MulDatasourceTest {

    @Autowired
    AccountService accountService;

    @GetMapping("/db1")
    public ResponseBase list1() {
        return accountService.listDb1();
    }

    @GetMapping("/db2")
    public ResponseBase list2() {
        return accountService.listDb2();
    }

    @GetMapping("/listPage")
    public ResponseBase listPage() {
        return accountService.listDPage();
    }



}
