package com.jianfei.shop.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author pangjianfei
 * create time 2018/8/9
 * session操作的工具类
 */
public class SessionUtils {
    /**
     * 判断是不是合法的用户
     * @param request
     * @return
     */
    public static boolean isLegalUser(HttpServletRequest request) {
        return StringUtils.isNotBlank(request.getSession().getAttribute("status").toString());
    }

    /**
     * 获取userId
     * @return
     */
    public static Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Long userId = Long.parseLong((String) session.getAttribute("userId"));
        return userId;
    }
}
