package com.access.communication;


import com.access.api.model.AccessDevInitDto;
import com.access.api.service.AccessDevService;
import com.access.api.service.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Reference
    private HelloService helloService;

    @Reference
    private AccessDevService accessDevService;

    @RequestMapping(value = "/hello")
    public String hello() {
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("BJQ"));
        return hello;
    }

    @RequestMapping(value = "/init")
    public List<AccessDevInitDto> init() {
        List<AccessDevInitDto> devInitDate = accessDevService.getDevInitDate("10047");

        System.out.println(helloService.sayHello(devInitDate.toString()));
        return devInitDate;
    }

}
