package com.jianfei.shop.service.impl;

import com.jianfei.shop.model.po.User;
import com.jianfei.shop.mybatis.dao.UserDao;
import com.jianfei.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pangjianfei
 * create time 2018/7/20
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    /**
     * 获取用户信息
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User getUser(String userName, String password) {
        return userDao.getUserByUserNameAndPassword(userName,password);
    }
}
