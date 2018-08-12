package com.jianfei.shop.service;

import com.jianfei.shop.model.dto.GoodsOrderResult;

/**
 * @author pangjianfei
 * create time 2018/7/31
 * 商品的相关接口
 */
public interface GoodsService {
    /**
     * 创建订单
     * @param id
     * @return
     */
    GoodsOrderResult createOrder(String id);

    /**
     * 库存检测，检测商品的剩余库存量
     * @param id
     * @return
     */
    boolean inventoryDetection(String id);
}
