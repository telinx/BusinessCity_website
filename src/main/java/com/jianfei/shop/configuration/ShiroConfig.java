package com.jianfei.shop.configuration;

import com.google.common.collect.Maps;
import org.apache.shiro.mgt.DefaultSecurityManager;
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
     * cas server地址
     */
    public static final String CAS_SERVER_URL_PREFIX = "http://127.0.0.1";

    /**
     * CAS登录页面地址
     */
    public static final String CAS_LOGIN_URL = CAS_SERVER_URL_PREFIX + "/login";

    /**
     * CAS注销页面地址
     */
    public static final String CAS_LOGOUT_URL = CAS_SERVER_URL_PREFIX + "/logout";

    /**
     * 当前工程对外提供的服务地址
     */
    public static final String SHIRO_SERVER_URL_PREFIX = "http://127.0.1.28:8080";

    /**
     * casFilter UrlPattern
     */
    public static final String CAS_FILTER_URL_PATTERN = "/index";

    /**
     *  登录地址
     */
    public static final String LOGIN_URL = CAS_LOGIN_URL + "?service=" + SHIRO_SERVER_URL_PREFIX + CAS_FILTER_URL_PATTERN;

    /**
     * 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用
     * cas.logout.followServiceRedirects=true）
     */
    public static final String LOGOUT_URL = CAS_LOGIN_URL+"?service="+LOGIN_URL;

    /**
     * 登录成功地址
     */
    public static final String LOGIN_SUCCESS_URL = "/index";
    /**
     * 权限认证失败跳转地址
     */
    public static final String UNAUTHORIZED_URL = "/error/403.html";

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
        shiroFilterFactoryBean.setLoginUrl("/login");
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
        filterChainDefinationMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinationMap);
        logger.info("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 配置SecurityManager，Subject交给该类进行处理
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }



    /**
     * 进行身份认证的Realm,配置为cas Realm,已经集成了单点登录的功能
     * @return
     */
    @Bean
    public Realm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //设置cas登录服务器地址前缀
        myShiroRealm.setCasServerUrlPrefix(CAS_SERVER_URL_PREFIX);
        //客户端回调地址，登录成功后的跳转地址
        myShiroRealm.setCasService(CAS_SERVER_URL_PREFIX+CAS_FILTER_URL_PATTERN);
        myShiroRealm.setDefaultRoles("common");
        return myShiroRealm;
    }

}
