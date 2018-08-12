package com.jianfei.shop.model.dto;

import com.jianfei.shop.model.po.OrderDetail;

/**
 * @author pangjianfei
 * create time 2018/8/12
 * 商品订单的下单结果，主要用来进行数据的传输
 */
public class GoodsOrderResult {

    /**
     * 下单是否成功
     */
    private boolean orderResult;

    /**
     * 订单的详细信息
     */
    private OrderDetail orderDetail;

    public boolean isOrderResult() {
        return orderResult;
    }

    public void setOrderResult(boolean orderResult) {
        this.orderResult = orderResult;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
