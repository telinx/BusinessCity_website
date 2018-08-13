package com.jianfei.shop.service.impl;

import com.jianfei.shop.cache.GoodsInventoryCache;
import com.jianfei.shop.model.dto.GoodsOrderResult;
import com.jianfei.shop.model.po.OrderDetail;
import com.jianfei.shop.service.GoodsService;
import com.jianfei.shop.utils.SessionUtils;
import org.springframework.stereotype.Service;

/**
 * @author pangjianfei
 * create time 2018/7/31
 * 商品的相关业务逻辑处理
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    /**
     * 创建订单
     * @param id
     * @return
     */
    @Override
    public GoodsOrderResult createOrder(String id) {
        //如果依然有库存
        if(inventoryDetection(id)) {
            //创建订单
            OrderDetail detail = new OrderDetail();
            detail.setUserId(SessionUtils.getUserId());
        }
        return null;
    }

    /**
     * 商品的库存检测
     * @param id
     * @return
     */
    @Override
    public boolean inventoryDetection(String id) {
        return GoodsInventoryCache.getInventoryById(id) > 0 ? true : false;
    }
}
