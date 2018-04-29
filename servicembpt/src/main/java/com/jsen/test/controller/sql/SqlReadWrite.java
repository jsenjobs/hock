package com.jsen.test.controller.sql;

import com.jsen.test.service.DatacontainerService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/sqlRW")
public class SqlReadWrite {

    @Autowired
    DatacontainerService datacontainerService;

    @GetMapping("/inserts/{num}")
    public ResponseBase inserts(@PathVariable("num") int num) {
        return datacontainerService.wrInserts(num);
    }

    @GetMapping("/update/{id}/{data}")
    public ResponseBase update(@PathVariable("id") int id, @PathVariable("data") String data) {
        return datacontainerService.wrUpdate(id, data);
    }

    @GetMapping("/delete/{id}")
    public ResponseBase delete(@PathVariable("id") int id) {
        return datacontainerService.wrDelete(id);
    }

    @GetMapping("/list")
    public ResponseBase list() {
        return datacontainerService.wrList();
    }
}
