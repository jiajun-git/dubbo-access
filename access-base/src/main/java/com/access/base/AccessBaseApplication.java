package com.access.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan(value = "com.access.base")
@MapperScan(basePackages = {"com.access.base.mapper"})
public class AccessBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessBaseApplication.class, args);
    }

}
