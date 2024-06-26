package com.dogmall.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan(basePackages = "com.dogmall.demo.Mapper")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MollysApplication {

	public static void main(String[] args) {
		SpringApplication.run(MollysApplication.class, args);
	}

}
