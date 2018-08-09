package com.jianfei.shop.controller.user;

import com.alibaba.fastjson.JSON;
import com.jianfei.shop.model.standard.StandardJsonObject;
import com.jianfei.shop.mybatis.entity.User;
import com.jianfei.shop.service.UserService;
import com.jianfei.shop.utils.JsonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pangjianfei
 * create time 2018/7/20
 * 非Restful接口,用户操作
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户
     * @param userName
     * @param password
     * @param request
     * @param response
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public void getUser(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.getUser(userName,password);
        StandardJsonObject standardJsonObject = new StandardJsonObject();
        standardJsonObject.setRet(user == null ? false : true);
        standardJsonObject.setData(user);
        try {
            JsonUtils.writeJsonToResponse(response, JSON.toJSONString(standardJsonObject));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 登录
     * @param username
     * @param password
     * @param response
     */
    @RequestMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password,HttpServletResponse response){
        logger.info("{}{}",username,password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()) {
                JsonUtils.writeJsonToResponse(response, JSON.toJSONString("使用shiro登录成功"));
            }
        }catch (AuthenticationException e) {
            e.printStackTrace();
            logger.info("登录验证失败");
        }
    }

    @RequestMapping("/loginPage")
    public String toLoginPage(){
        return "login";
    }
}
