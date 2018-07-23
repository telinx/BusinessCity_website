package com.jianfei.shop.mybatis.entity;

import java.util.List;

/**
 * @author pangjianfei
 * create time 2018/7/23
 */
public class Role {

    private Integer id;

    private String role;

    private String desc;

    private List<SysPermission> permissionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
