package com.jsen.test.config.shiro.conf;

import com.google.common.collect.Maps;
import com.jsen.test.entity.SysFilterChain;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/8
 */
public class JShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    public void setFilterChainDefinitionMap(List<SysFilterChain> list) {
        Map<String, String> filterRuleMap = Maps.newHashMap();

        // filterRuleMap.put("/account/login/**", "anon");

        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/v2/**", "anon");

        filterRuleMap.put("/sysUser/login/**", "anon");
        filterRuleMap.put("/pub/**", "anon");
        filterRuleMap.put("/static/**", "anon");
        filterRuleMap.put("/401/**", "anon");
        list.forEach(fC -> {
            System.err.println(fC.getUrl());
            filterRuleMap.put(fC.getUrl(), fC.getFilters());
        });
        filterRuleMap.put("/**", "jwt");
        // filterRuleMap.put("/**", "anon");
        // filterRuleMap.put("/shiro/**", "jwt,perms[table:edite],roles[admin]");

        super.setFilterChainDefinitionMap(filterRuleMap);
    }
}
