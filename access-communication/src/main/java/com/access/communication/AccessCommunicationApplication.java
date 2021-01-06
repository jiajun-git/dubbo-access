package com.access.communication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class AccessCommunicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessCommunicationApplication.class, args);
    }

}
