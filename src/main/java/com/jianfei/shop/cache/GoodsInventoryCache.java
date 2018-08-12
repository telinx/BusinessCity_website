package com.jianfei.shop.cache;

import com.google.common.collect.Maps;
import com.jianfei.shop.enums.GoodsStatus;
import com.jianfei.shop.service.DBToCacheService;
import com.jianfei.shop.utils.MapConvertUtil;
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
    private static final ConcurrentHashMap<String,Integer> inventoryMap;

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
        inventoryMap = MapConvertUtil.converMapToConcurrentHashMap(integerMap);
    }

    /**
     * 获取某个商品的库存量
     * @param id
     * @return
     */
    public static int getInventoryById(String id) {
        return inventoryMap.get(id);
    }

    /**
     * 更新某个商品的库存量(1、下架，更新为0 2、销售，库存减1 3、新上架，导入库存)
     */
    public static void updateInventoryById(String id, Integer count, GoodsStatus status) {
        //如果是上架的商品
        if(status == GoodsStatus.ON_SALE) {
            if (inventoryMap.containsKey(id)) {
                int value = inventoryMap.get(id);
                inventoryMap.put(id, count == null || count < 0 ? value-1 : count);
            }else {
                inventoryMap.put(id, count == null || count < 0 ? 0 : count);
            }
        }else if (status == GoodsStatus.OFF_SALE) {
            inventoryMap.put(id,0);
        }
    }

}
