package com.jianfei.shop.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author pangjianfei
 * create time 2018/8/12
 * DB与缓存进行交互的service
 */
public interface DBToCacheService {

    /**
     * 同步库存情况到缓存
     * @return
     */
    Map<String,Integer> synchronizeInventoryToCache();
}
