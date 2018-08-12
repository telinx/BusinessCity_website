package com.jianfei.shop.mybatis.dao;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author pangjianfei
 * create time 2018/8/13
 * 商品的数据持久操作
 */
@Repository
public interface GoodsDao {

    /**
     * 获取所有的商品的库存
     * @return
     */
    @MapKey("id")
    Map<String,Integer> getAllGoodsInventory();
}
