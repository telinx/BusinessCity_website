package com.jianfei.shop.configuration;

import com.google.common.collect.Maps;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author pangjianfei
 * create time 2018/7/11
 * desc:shiro的相关配置，这里也对shiro的单点登录进行了配置
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);


    /**
     * shiro中较为核心的类，拦截subject的操作进行相关的处理，然后判断是否提交给SecurityManager进行处理
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置会默认寻找Web工程目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/user/loginPage");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String,String> filterChainDefinationMap = Maps.newLinkedHashMap();
        //配置不会被拦截的链接，按照顺序进行判断
        filterChainDefinationMap.put("/static/**", "anon");
        filterChainDefinationMap.put("/ajaxLogin", "anon");

        //配置退出过滤器,具体退出的实现shiro已经进行了封装
        filterChainDefinationMap.put("/logout","logout");
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinationMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinationMap);
        logger.info("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 进行身份认证的Realm
     * @return
     */
    @Bean
    public Realm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }
}