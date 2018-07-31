package com.jianfei.shop.restful;

import org.springframework.web.bind.annotation.*;

/**
 * @author pangjianfei
 * create time 2018/7/22
 * user controll的rest实现
 */
@RestController(value = "/user")
public class UserRestController {

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable Integer id){
        return null;
    }
}
