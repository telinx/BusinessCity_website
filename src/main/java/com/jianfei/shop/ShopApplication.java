package com.jianfei.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author pangjianfei
 * Date:2018-07-05
 * spring-boot应用启动入口
 */
@SpringBootApplication
//@ServletComponentScan
//@Configuration
@MapperScan("com.jianfei.shop.mybatis.dao")
public class ShopApplication {

	private static final Logger logger = LoggerFactory.getLogger(ShopApplication.class);

	public static void main(String[] args) {
		logger.info("spring-boot,web商城开始启动");
		SpringApplication.run(ShopApplication.class, args);
	}
}
