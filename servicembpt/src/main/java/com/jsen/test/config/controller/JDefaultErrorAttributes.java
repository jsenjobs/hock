package com.jsen.test.config.controller;

import com.google.common.collect.Maps;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/8
 */
public class JDefaultErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest requestAttributes, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = Maps.newLinkedHashMap();
        errorAttributes.put("code", 1);
        Throwable error = this.addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
        if (error != null) {
            if (error instanceof AuthenticationException) {
                errorAttributes.put("hcode", 401);
                if (!StringUtils.isEmpty(error.getMessage())) {
                    errorAttributes.put("msg", error.getMessage());
                } else {
                    errorAttributes.put("msg", "认证失败");
                }
            } else if (error instanceof UnauthorizedException) {
                errorAttributes.put("hcode", 401);
                if (!StringUtils.isEmpty(error.getMessage())) {
                    errorAttributes.put("msg", error.getMessage());
                } else {
                    errorAttributes.put("msg", "缺少权限");
                }
            } else {
                this.addStatus(errorAttributes, requestAttributes);
                if (!StringUtils.isEmpty(error.getMessage())) {
                    errorAttributes.put("msg", error.getMessage());
                } else {
                    errorAttributes.put("msg", "缺少权限");
                }
            }
        } else { // error is null
            this.addStatus(errorAttributes, requestAttributes);
            Object message = this.getAttribute(requestAttributes, "javax.servlet.error.message");
            if (!StringUtils.isEmpty(message) || errorAttributes.get("msg") == null) {
                errorAttributes.put("msg", StringUtils.isEmpty(message) ? "null" : message);
            }
        }
        // errorAttributes.put("timestamp", new Date());
        // this.addStatus(errorAttributes, requestAttributes);
        this.addPath(errorAttributes, requestAttributes);
        return errorAttributes;
    }

    private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Integer status = this.getAttribute(requestAttributes, "javax.servlet.error.status_code");
        if (status == null) {
            errorAttributes.put("hcode", 999);
            // errorAttributes.put("error", "null");
            // errorAttributes.put("status", 999);
            // errorAttributes.put("error", "None");
        } else {
            errorAttributes.put("hcode", status);
            /*
            try {
                errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
            } catch (Exception var5) {
                errorAttributes.put("error", "Http Status " + status);
            }
            */

        }
    }

    private Throwable addErrorDetails(Map<String, Object> errorAttributes, WebRequest requestAttributes, boolean includeStackTrace) {
        Throwable error = this.getError(requestAttributes);

        if (error != null) {
            while(true) {
                if (!(error instanceof ServletException) || error.getCause() == null) {
                    errorAttributes.put("exception", error.getClass().getName());
                    this.addErrorMessage(errorAttributes, error);
                    if (includeStackTrace) {
                        this.addStackTrace(errorAttributes, error);
                    }
                    break;
                }

                error = error.getCause();
            }
        }

        Object message = this.getAttribute(requestAttributes, "javax.servlet.error.message");
        if ((!StringUtils.isEmpty(message) || errorAttributes.get("msg") == null) && !(error instanceof BindingResult)) {
            errorAttributes.put("msg", StringUtils.isEmpty(message) ? "null" : message);
        }
        return error;
    }

    private void addPath(Map<String, Object> errorAttributes,
                         RequestAttributes requestAttributes) {
        String path = getAttribute(requestAttributes, "javax.servlet.error.request_uri");
        if (path != null) {
            errorAttributes.put("api", path);
        }
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
        BindingResult result = this.extractBindingResult(error);
        if (result == null) {
            errorAttributes.put("msg", error.getMessage());
        } else {
            if (result.getErrorCount() > 0) {
                errorAttributes.put("errors", result.getAllErrors());
                errorAttributes.put("msg", "Validation failed for object='" + result.getObjectName() + "'. Error count: " + result.getErrorCount());
            } else {
                errorAttributes.put("msg", "No errors");
            }

        }
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put("trace", stackTrace.toString());
    }

    private BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, 0);
    }
}
