package com.access.base;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDubboConfiguration
@SpringBootApplication
@MapperScan(basePackages = {"com.access.base.mapper"})
public class AccessBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessBaseApplication.class, args);
    }

}
