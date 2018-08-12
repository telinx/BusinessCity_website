package com.jianfei.shop.service;

import com.jianfei.shop.model.po.User;

/**
 * @author pangjianfei
 * create time 2018/7/20
 */
public interface UserService {
    /**
     * 获取用户信息
     * @param userName
     * @param password
     * @return
     */
    User getUser(String userName, String password);
}
