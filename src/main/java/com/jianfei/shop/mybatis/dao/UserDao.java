package com.jianfei.shop.mybatis.dao;

import com.jianfei.shop.model.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pangjianfei
 * create time 2018/7/20
 */
@Mapper
public interface UserDao {

    User getUserByUserNameAndPassword(String userName, String password);
}
