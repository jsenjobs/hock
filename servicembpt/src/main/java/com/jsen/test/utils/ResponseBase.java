package com.jsen.test.utils;

import com.alibaba.fastjson.JSONObject;

public class ResponseBase extends JSONObject {
    private ResponseBase() {
    }
    public static ResponseBase create() {
        return new ResponseBase();
    }

    public ResponseBase add(String key, Object value) {
        put(key, value);
        return this;
    }

    public ResponseBase code(int code) {
        return add("code", code);
    }
    public ResponseBase data(Object data) {
        return add("data", data);
    }
    public ResponseBase msg(String msg) {
        return add("msg", msg);
    }
    public ResponseBase hcode(int hcode) {
        return add("hcode", hcode);
    }
    public ResponseBase error(String error) {
        return add("error", error);
    }


}
