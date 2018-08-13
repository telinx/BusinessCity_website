package com.jianfei.shop.utils;

import com.google.common.collect.Maps;
import com.jianfei.shop.model.standard.StandardJsonObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author pangjianfei
 * create time 2018/8/13
 * 全局异常处理
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public StandardJsonObject exceptionHandler(Exception e) {
        StandardJsonObject sjo = new StandardJsonObject();
        sjo.setRet(false);
        sjo.setData(null);
        sjo.setErrMsg("系统发生异常，程序员同学正在赶来。。。。");
        return  sjo;
    }
}
