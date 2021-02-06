package com.access.communication.netty.udp.handler;

import com.access.communication.service.AccessService;
import com.access.communication.utils.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class AccessChannelHandler extends ChannelInboundHandlerAdapter {

    private AccessService accessService;

    public AccessChannelHandler(AccessService accessService) {
        this.accessService = accessService;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try {
            DatagramPacket packet = (DatagramPacket) msg;
            ByteBuf byteBuf = packet.copy().content();
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String content = HexUtils.byteToHexString(bytes);
            byteBuf.release();
            InetSocketAddress recipient = packet.sender();
            accessService.handleData(ctx, content, recipient);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("active==" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("inactive==" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

}
