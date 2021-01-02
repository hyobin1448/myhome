package com.neo.myhome.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {
	
	@Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
          .driverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy")
          .url("jdbc:log4jdbc:mariadb://localhost:3307/mydb")
          .username("myadmin")
          .password("success")
          .build(); 
    }
}