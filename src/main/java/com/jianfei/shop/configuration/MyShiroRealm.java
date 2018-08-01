package com.jianfei.shop.configuration;

import com.jianfei.shop.mybatis.entity.Role;
import com.jianfei.shop.mybatis.entity.SysPermission;
import com.jianfei.shop.mybatis.entity.User;
import com.jianfei.shop.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author pangjianfei
 * create time 2018/7/12
 * 认证的Realm，在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理
 * 在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。
 * 可以说，Realm是专用于安全框架的DAO.
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 进行授权：权限查询
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = null;
        logger.info("开始进行权限查询");
        User userInfo  = (User)principalCollection.getPrimaryPrincipal();
        for(Role role : userInfo.getRole()){
            simpleAuthorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissionList()){
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证：登录操作是进行身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        logger.info("开始进行身份认证：MyShiroRealm.doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        User user = userService.getUser(name,password);
        logger.info("登录验证结果：{}",user.toString());
        if(user != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal =  user.getUsername();
            Object credentials = user.getPassword();

            // 获取盐值，即用户名
            ByteSource salt = ByteSource.Util.bytes(name);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            simpleAuthenticationInfo  =
                    new SimpleAuthenticationInfo(principal,credentials,salt,realmName);
        }else {
            throw new AuthenticationException();
        }
        return simpleAuthenticationInfo;
    }
}
