package com.jianfei.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pangjianfei
 * Date:2018-07-05
 * spring-boot应用启动入口
 */
@SpringBootApplication
public class ShopApplication {

	private static final Logger logger = LoggerFactory.getLogger(ShopApplication.class);

	public static void main(String[] args) {
		logger.info("spring-boot,web商城开始启动");
		SpringApplication.run(ShopApplication.class, args);
	}
}
