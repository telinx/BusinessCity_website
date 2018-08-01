package com.jianfei.shop.controller.goods;

import com.jianfei.shop.model.standard.StandardJsonObject;
import com.jianfei.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author pangjianfei
 * create time 2018/7/31
 * 商品相关的controller
 */
@RequestMapping("/goodsOperate")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger("serviceLog");

    @Autowired
    private GoodsService goodsService;

    /**
     * 购买商品
     * @param id
     * @return
     */
    @RequestMapping(value = "/buy")
    @ResponseBody
    public StandardJsonObject buyGoodsById(String id){

        StandardJsonObject standardJsonObject = new StandardJsonObject();
        return  standardJsonObject;
    }

    @RequestMapping(value = "/detail")
    public String getPageDetail(String id){
        logger.info("get goods detail");
        return "/goods/goodsDetail";
    }
}
