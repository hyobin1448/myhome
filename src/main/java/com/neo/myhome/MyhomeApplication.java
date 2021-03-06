package com.neo.myhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.neo.myhome")
public class MyhomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyhomeApplication.class, args);
	}

}
