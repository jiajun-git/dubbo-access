package com.access.communication.controller;


import com.access.api.model.AccessDevInitDto;
import com.access.api.service.AccessDevService;
import com.access.api.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Reference(version = "1.0.0")
    private AccessDevService accessDevService;

    @RequestMapping(value = "/hello")
    public String hello() {
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("BJQ"));
        return hello;
    }

    @RequestMapping(value = "/init")
    public List<AccessDevInitDto> init() {
        List<AccessDevInitDto> devInitDate = accessDevService.getDevInitDate("10021");

        System.out.println(helloService.sayHello(devInitDate.toString()));
        return devInitDate;
    }

}
