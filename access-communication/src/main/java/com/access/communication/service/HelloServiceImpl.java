package com.access.communication.service;

import com.access.api.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

//dubbo注解，暴露服务
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
