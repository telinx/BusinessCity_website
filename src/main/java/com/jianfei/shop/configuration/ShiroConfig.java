package com.jianfei.shop.configuration;

import com.google.common.collect.Maps;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
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
     * 登录地址
     */
    public static final String LOGIN_URL = CAS_LOGIN_URL + "?service=" + SHIRO_SERVER_URL_PREFIX + CAS_FILTER_URL_PATTERN;

    /**
     * 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用
     * cas.logout.followServiceRedirects=true）
     */
    public static final String LOGOUT_URL = CAS_LOGIN_URL + "?service=" + LOGIN_URL;

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
     *
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
        Map<String, String> filterChainDefinationMap = Maps.newLinkedHashMap();
        //配置不会被拦截的链接，按照顺序进行判断
        filterChainDefinationMap.put("/static/**", "anon");
        filterChainDefinationMap.put("/ajaxLogin", "anon");

        //配置退出过滤器,具体退出的实现shiro已经进行了封装
        filterChainDefinationMap.put("/logout", "logout");
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinationMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinationMap);
        logger.info("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 配置SecurityManager，Subject交给该类进行处理
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    /**
     * 进行身份认证的Realm,配置为cas Realm,已经集成了单点登录的功能
     *
     * @return
     */
    @Bean
    public Realm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //设置cas登录服务器地址前缀
        myShiroRealm.setCasServerUrlPrefix(CAS_SERVER_URL_PREFIX);
        //客户端回调地址，登录成功后的跳转地址
        myShiroRealm.setCasService(CAS_SERVER_URL_PREFIX + CAS_FILTER_URL_PATTERN);
        myShiroRealm.setDefaultRoles("common");
        return myShiroRealm;
    }

    /**
     * 注册单点登出的Listener,优先级要高于cas的filter,所以设置为最高的优先级
     *
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ServletListenerRegistrationBean<?> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SingleSignOutHttpSessionListener());
        bean.setEnabled(true);
        return bean;
    }

    /**
     * 注册单点登出的filter
     * FilterRegistrationBean可以对filter进行排序
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<?> filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("singleSignOutFilter");
        bean.setFilter(new SingleSignOutFilter());
        bean.setEnabled(true);
        bean.addUrlPatterns("/*");
        return bean;
    }

    /**
     * 注册DelegatingFilterProxy
     *
     * @return
     */
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //DelegatingFilterProxy提供了在 web.xml和applicationcontext之间的联系,这里filter是配置在类似于web.xml中
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * 下面两个配置主要用来开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * CAS过滤器
     *
     * @return
     */
    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
        casFilter.setFailureUrl(LOGIN_URL);// 我们选择认证失败后再打开登录页面
        casFilter.setLoginUrl(LOGIN_URL);
        return casFilter;
    }

    /**
     * 使用工厂模式，创建并初始化ShiroFilter
     *
     * @param securityManager
     * @param casFilter
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, CasFilter casFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(LOGIN_URL);
        /*
         *  登录成功后要跳转的连接，不设置的时候，会默认跳转到前一步的url
         *  比如先在浏览器中输入了http://localhost:8080/userlist,但是现在用户却没有登录，于是会跳转到登录页面，等登录认证通过后，
         *  页面会再次自动跳转到http://localhost:8080/userlist页面而不是登录成功后的index页面
         *  建议不要设置这个字段
         */
//        shiroFilterFactoryBean.setSuccessUrl(loginSuccessUrl);

        // 设置无权限访问页面
        shiroFilterFactoryBean.setUnauthorizedUrl(UNAUTHORIZED_URL);
        /*
         *  添加casFilter到shiroFilter中，注意，casFilter需要放到shiroFilter的前面，
         *  从而保证程序在进入shiro的login登录之前就会进入单点认证
         */
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("casFilter", casFilter);

        // logout已经被单点登录的logout取代
        // filters.put("logout",logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }


    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）,角色/权限信息由MyShiroCasRealm对象提供doGetAuthorizationInfo实现获取来的
     * 生产中会将这部分规则放到数据库中
     *
     * @param shiroFilterFactoryBean
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        /////////////////////// 下面这些规则配置最好配置到配置文件中，注意，此处加入的filter需要保证有序，所以用的LinkedHashMap ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.put(CAS_FILTER_URL_PATTERN, "casFilter");

        //2.不拦截的请求
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        // 此处将logout页面设置为anon，而不是logout，因为logout被单点处理，而不需要再被shiro的logoutFilter进行拦截
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        //3.拦截的请求（从本地数据库获取或者从casserver获取(webservice,http等远程方式)，看你的角色权限配置在哪里）
        filterChainDefinitionMap.put("/user", "authc"); //需要登录

        //4.登录过的不拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

    }
}