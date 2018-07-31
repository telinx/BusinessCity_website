package com.jianfei.shop.restful.seckill;

import com.jianfei.shop.model.standard.StandardJsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pangjianfei
 * create time 2018/7/31
 * 商品秒杀
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsSecKillController {

    /**
     * 购买商品通过id,秒杀的核心接口
     * @return
     */
    @GetMapping(value = "/buy/{id}")
    public StandardJsonObject buyGoodsWithId(@PathVariable String id){

        return null;
    }
}
