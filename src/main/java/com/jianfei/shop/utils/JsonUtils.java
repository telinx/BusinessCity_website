package com.jianfei.shop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pangjianfei
 * create time 2018/7/21
 * json操作的工具类
 */
public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 将json数据返回到前端
     * @param response
     * @param result
     */
    public static void writeJsonToResponse(HttpServletResponse response,String result) {
        PrintWriter pw = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            pw = response.getWriter();
            pw.write(result.toString());
            pw.flush();
        }catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            if(pw != null) {
                pw.close();
            }
        }
    }
}
