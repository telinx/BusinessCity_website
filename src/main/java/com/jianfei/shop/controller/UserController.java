package com.jianfei.shop.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianfei.shop.model.standard.StandardJsonObject;
import com.jianfei.shop.mybatis.entity.User;
import com.jianfei.shop.service.UserService;
import com.jianfei.shop.utils.JsonUtils;
import com.rabbitmq.tools.json.JSONUtil;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
