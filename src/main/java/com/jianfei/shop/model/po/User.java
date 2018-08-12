package com.jianfei.shop.model.po;

import java.util.Date;
import java.util.Set;

/**
 * @author pangjianfei
 * create time 2018/7/20
 * 用户实体
 */

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

    //一个用户对应多个角色
    private Set<Role> role;

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<Role> getRole() {
        return role;
    }
}
