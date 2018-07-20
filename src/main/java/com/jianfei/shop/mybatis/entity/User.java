package com.jianfei.shop.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.search.DocValueFormat;

import java.util.Date;

/**
 * @author pangjianfei
 * create time 2018/7/20
 * 用户实体
 */
@Setter
@Getter
public class User {
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //账户创建时间
    private String createTime;
    //上次登录时间
    private Date lastLoginTime;
    //当前状态
    private Integer status;

    public User(){}
}
