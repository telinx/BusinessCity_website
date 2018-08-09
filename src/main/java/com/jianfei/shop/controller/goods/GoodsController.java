package com.jianfei.shop.controller.goods;

import com.jianfei.shop.model.standard.StandardJsonObject;
import com.jianfei.shop.service.GoodsService;
import com.jianfei.shop.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public StandardJsonObject buyGoodsById(String id, HttpServletRequest request){
        StandardJsonObject standardJsonObject = new StandardJsonObject();
        logger.info("buy,{}",id);
        HttpSession session = request.getSession();
        logger.info("session is null:{}",session == null);
        if(session != null) {
            logger.info("session中的信息为{}{}",session.getId(),session.getAttributeNames().nextElement());
            logger.info("session中存储的数据为{}",session.getAttribute(session.getAttributeNames().nextElement()));
        }
        //进行身份认证,如果是合法的用户，那么进行下单
        if(SessionUtils.isLegalUser(request)) {

        }else {
            standardJsonObject.setRet(false);
            standardJsonObject.setErrMsg("不是合法的用户");
        }

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
