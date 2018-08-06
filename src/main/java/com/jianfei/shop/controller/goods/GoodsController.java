package com.jianfei.shop.controller.goods;

import com.jianfei.shop.model.standard.StandardJsonObject;
import com.jianfei.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author pangjianfei
 * create time 2018/7/31
 * 商品相关的controller
 */
@RequestMapping("/goodsOperate")
@Controller
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
        logger.info("buy,{}",id);
        StandardJsonObject standardJsonObject = new StandardJsonObject();
        return  standardJsonObject;
    }

    /**
     * 获取商品的详情页
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail")
    public ModelAndView getPageDetail(String id){
        logger.info("get goods detail,{}",id);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/goods/goodsDetail");
        return mv;
    }
}
