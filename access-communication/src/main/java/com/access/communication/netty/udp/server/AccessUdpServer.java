package com.access.communication.netty.udp.server;

import com.access.communication.netty.udp.handler.AccessChannelHandler;
import com.access.communication.service.AccessService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class AccessUdpServer{

    public AccessService accessService;

    public AccessUdpServer(AccessService accessService) {
        this.accessService = accessService;
    }

    private static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    @PostConstruct
    public void start(){
        log.info("门禁通信服务UDP模式启动!");
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                    .handler(new AccessChannelHandler(accessService));
            bootstrap.bind(9999).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        eventLoopGroup.shutdownGracefully().sync();
    }
}
