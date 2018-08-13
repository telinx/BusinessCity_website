package com.jianfei.shop.model.po;

import java.sql.Date;

/**
 * @author pangjianfei
 * create time 2018/8/12
 * 订单详情
 */
public class OrderDetail {
    /**订单编号*/
    private Long id ;
    /**商品id*/
    private Long goodsId;
    /**订单创建时间*/
    private Date createTime;
    /**创建订单的用户*/
    private Long userId;
    /**订单状态 0有效 1待支付 2订单取消 3订单完成*/
    private Short status;
    /**联系方式*/
    private String mobile;
    /**快递号*/
    private String expressNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
}
