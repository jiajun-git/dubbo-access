package com.access.base;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class AccessBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessBaseApplication.class, args);
    }

}
