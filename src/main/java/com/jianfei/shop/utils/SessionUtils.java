package com.jianfei.shop.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest; /**
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
}
