package com.jsen.test.controller.sql;


import com.jsen.test.service.DatacontainerService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jsen
 */
@RestController
@RequestMapping("/sql/batch")
@CrossOrigin
public class SqlBatch {
    @Autowired
    DatacontainerService datacontainerService;

    @GetMapping("/batchInsert/{num}/{trans}")
    public ResponseBase batchInsert(@PathVariable("num") int num, @PathVariable("trans") boolean trans) {
        return datacontainerService.insertBatch(num, trans);
    }

    @GetMapping("/batchInsert2/{num}")
    public ResponseBase batchInsert2(@PathVariable("num") int num) {
        return datacontainerService.insertBatch2(num);
    }

    @GetMapping("/batchInsert3/{num}")
    public ResponseBase batchInsert3(@PathVariable("num") int num) {
        return datacontainerService.insertBatch3(num);
    }

    @GetMapping("/insert/{num}/{trans}")
    public ResponseBase insert(@PathVariable("num") int num, @PathVariable("trans") boolean trans) {
        return datacontainerService.insert(num, trans);
    }




    @GetMapping("/trans/timeout/{num}/{sleep}")
    public ResponseBase transTimeout(@PathVariable("num") int num, @PathVariable("sleep") long sleep) {
        return datacontainerService.transTimeout(num, sleep);
    }

    @GetMapping("/trans/reanonly/{num}")
    public ResponseBase transReadOnly(@PathVariable("num") int num) {
        return datacontainerService.transReadOnly(num);
    }

    @GetMapping("/trans/exception/{num}")
    public ResponseBase insert(@PathVariable("num") int num) {
        return datacontainerService.transNoRollBackException(num);
    }
}
