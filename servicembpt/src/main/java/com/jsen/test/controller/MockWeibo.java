package com.jsen.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@CrossOrigin
@RequestMapping("/weibo")
public class MockWeibo {

    JSONArray weiboData = null;
    @RequestMapping("/get")
    public JSONArray weiboData() {
        if (weiboData == null) {
            /*
            * URL url = MyTest.class.getClassLoader().getResource("userFile.properties");

File file = new File(url.getFile());*/
            try(BufferedReader is = new BufferedReader(new InputStreamReader(MockWeibo.class.getClassLoader().getResourceAsStream("weibo.json")))) {
                StringBuilder data = new StringBuilder();
                String line;
                while ((line = is.readLine()) != null) {
                    data.append(line);
                }
                weiboData = JSON.parseArray(data.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return weiboData;
    }

}
