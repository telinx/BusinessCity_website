package com.jianfei.shop.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @author pangjianfei
 * create time 2018/8/13
 * Map转化工具类
 */
public class MapConvertUtil {

    /**
     * 将普通的HashMap转化为ConcurrentHashMap
     * @param integerMap
     * @return
     */
    public static ConcurrentHashMap<String,Integer> converMapToConcurrentHashMap(Map<String, Integer> integerMap) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Set<Map.Entry<String,Integer>> entrys = integerMap.entrySet();
        //使用流进行迭代，lambda表达式进行转化
        entrys.stream().forEach(stringIntegerEntry -> {concurrentHashMap.put(stringIntegerEntry.getKey(),stringIntegerEntry.getValue());});
        return concurrentHashMap;
    }
}
