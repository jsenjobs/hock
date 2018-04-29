package com.jsen.test.config;

import com.google.common.collect.Maps;
import com.jsen.test.config.shiro.ShiroFilter;
import com.jsen.test.config.shiro.ShiroRealm;
import com.jsen.test.config.shiro.conf.JSessionConf;
import com.jsen.test.config.shiro.conf.JShiroFilterFactoryBean;
import com.jsen.test.service.AccountService;
import com.jsen.test.service.SysFilterChainService;
import com.jsen.test.service.impl.SysFilterChainServiceImpl;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
@Service
@AutoConfigureAfter({AccountService.class})
public class XXXShiroConf {


    @Bean("sysFilterChainService")
    public SysFilterChainService getSysFilterChainService() {
        return new SysFilterChainServiceImpl();
    }


    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(ShiroRealm realm, JSessionConf jSessionConf) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setSessionManager(jSessionConf);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }


    @Bean("shiroFilter")
    public JShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        JShiroFilterFactoryBean factoryBean = new JShiroFilterFactoryBean();

        factoryBean.setSecurityManager(securityManager);

        // factoryBean.setLoginUrl("/pub/loginFailed");
        // factoryBean.setUnauthorizedUrl("/pub/loginFailed");

        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        // factoryBean.setLoginUrl("/account/login/**");
        // factoryBean.setUnauthorizedUrl("/401");
        // 登录成功后要跳转的链接
        // factoryBean.setSuccessUrl("/index");
        // 未授权界面;
        // factoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filterMap = Maps.newHashMap();
        filterMap.put("jwt", new ShiroFilter());
        factoryBean.setFilters(filterMap);

        // Map<String, String> filterRuleMap = Maps.newHashMap();
        /*
        // 配置不会被拦截的链接 顺序判断
        filterRuleMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        // filterRuleMap.put("/logout", "logout");

        // filterRuleMap.put("/shiro", "perms[table:edit]");
        // filterRuleMap.put("/**", "authc");
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/**", "jwt,authc");

        // filterRuleMap.put("/account/login/**", "anon");
        */


        /*
        filterRuleMap.put("/account/login/**", "anon");
        filterRuleMap.put("/pub/**", "anon");
        filterRuleMap.put("/static/**", "anon");
        filterRuleMap.put("/401/**", "anon");
        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/shiro/**", "jwt,perms[table:edite],roles[admin]");
        */
        factoryBean.setFilterChainDefinitionMap(getSysFilterChainService().listAll());

        // factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        factoryBean.setUnauthorizedUrl("/pub/loginFailed");

        return factoryBean;
    }



    /** * 下面的代码是添加注解支持 */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
