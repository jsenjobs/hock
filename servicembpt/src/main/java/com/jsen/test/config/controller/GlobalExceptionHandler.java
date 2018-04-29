package com.jsen.test.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.config.shiro.exception.TokenException;
import com.jsen.test.controller.exceptions.OutException;
import com.jsen.test.utils.ResponseBase;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public JSONObject authorizationException(AuthorizationException e) {
        return ResponseBase.create().code(1).hcode(401).msg("未授权").add("error", e.getMessage());
    }

    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public JSONObject authenticationException(AuthorizationException e) {
        return ResponseBase.create().code(1).hcode(401).msg("登入失败").add("error", e.getMessage());
    }

    @ExceptionHandler(OutException.class)
    @ResponseBody
    public ResponseBase _outException(OutException e) {
        return ResponseBase.create().code(1).msg("out exception handler").add("e", e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResponseBase sqlException(OutException e) {
        return ResponseBase.create().code(1).msg("sql 执行出错").add("error", e.getMessage());
    }

    /*
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JSONObject runtimeException(RuntimeException e) {
        return ResponseBase.create().code(1).hcode(401).msg("未授权").add("error", e.getMessage());
    }
    */
}
