package com.jianfei.shop.cache;

import com.google.common.collect.Maps;
import com.jianfei.shop.service.DBToCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pangjianfei
 * create time 2018/8/12
 * 商品的库存缓存
 */
@Component
public class GoodsInventoryCache {

    private static DBToCacheService dbToCacheService;

    /**
     * 创建缓存的容器，在JDK1.8中ConcurrentHashMap的实现发生了改变
     */
    private static final ConcurrentHashMap inventoryMap = new ConcurrentHashMap();

    @Autowired(required = true)
    public void setDbToCacheService(DBToCacheService dbToCacheService) {
        GoodsInventoryCache.dbToCacheService = dbToCacheService;
    }

    /**
     * 静态代码块，执行时机为类初始化的时候进行的，并不是在类进行装载的时候
     * 作用：同步数据库中的数据，存入key:value=商品id：库存数量
     */
    static {
        Map<String,Integer> integerMap = dbToCacheService.synchronizeInventoryToCache();
    }
}
