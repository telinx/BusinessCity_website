package com.jianfei.shop.restful;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pangjianfei
 * create time 2018/7/22
 * user controll的rest实现
 */
@RestController(value = "/userV1")
public class UserRestController {

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Integer id){
        return null;
    }
}
