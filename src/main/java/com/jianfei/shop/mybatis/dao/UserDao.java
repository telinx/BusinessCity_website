package com.jianfei.shop.mybatis.dao;

import com.jianfei.shop.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author pangjianfei
 * create time 2018/7/20
 */
@Mapper
public interface UserDao {

    User getUserByUserNameAndPassword(String userName, String password);
}
