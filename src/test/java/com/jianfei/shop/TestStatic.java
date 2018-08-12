package com.jianfei.shop;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author pangjianfei
 * create time 2018/8/12
 */
@SpringBootTest
public class TestStatic{
    private static Logger logger = LoggerFactory.getLogger(TestStatic.class);
    static {
        logger.info("执行静态代码块");
    }

    @Test
    public void testStatic(){
        //执行该测试方法会执行静态代码块，因为在执行这个方法的时候，进行了类的初始化
    }
}
