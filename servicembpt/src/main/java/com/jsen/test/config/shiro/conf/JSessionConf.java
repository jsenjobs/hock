package com.jsen.test.config.shiro.conf;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/8
 */
@Configuration
public class JSessionConf extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "Authorization";
    public static final String REFRRSH_SESSION_ID_SOURCE = "Stateless request";

    public JSessionConf() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFRRSH_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
        return super.getSessionId(request, response);
    }
}
