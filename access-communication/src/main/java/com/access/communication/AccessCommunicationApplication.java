package com.access.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(value = "com.access.communication")
public class AccessCommunicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessCommunicationApplication.class, args);
    }

}
