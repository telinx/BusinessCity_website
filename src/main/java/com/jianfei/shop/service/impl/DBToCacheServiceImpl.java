package com.jianfei.shop.service.impl;

import com.jianfei.shop.mybatis.dao.GoodsDao;
import com.jianfei.shop.service.DBToCacheService;
import com.jianfei.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author pangjianfei
 * create time 2018/8/12
 * DB与缓存进行数据同步的service
 */
@Service
public class DBToCacheServiceImpl implements DBToCacheService{

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 同步库存情况到缓存
     * @return
     */
    @Override
    public Map<String, Integer> synchronizeInventoryToCache() {
        return null;
    }
}
