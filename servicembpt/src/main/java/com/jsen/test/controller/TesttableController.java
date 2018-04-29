package com.jsen.test.controller;


import com.jsen.test.entity.Testtable;
import com.jsen.test.service.TesttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/testtable")
@CrossOrigin
public class TesttableController {

    @Autowired
    TesttableService testtableService;


    @RequestMapping("/test001")
    public List<Testtable> test1() {
        return testtableService.selectMyAll();
    }


    @RequestMapping("/test002")
    public List<Testtable> test2() {
        return testtableService.selectPart();
    }


    @RequestMapping("/listAll")
    public List<Testtable> listAll() {
        return testtableService.listAll();
    }

    @RequestMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") int id) {
        return testtableService.delete(id);
    }

    @RequestMapping("/list")
    public List<Testtable> list() {
        return testtableService.list();
    }

}

