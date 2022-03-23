package com.ake.nbems.eaps.netty;

import com.ake.nbems.eaps.netty.util.ChannelMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @Author lium
 * @Date 2022/3/23
 * @Description
 */
@Slf4j
@Component
public class NettyServerHandeler extends SimpleChannelInboundHandler<String> {

    // 数据包存储
    private final AttributeKey<StringBuffer> dataBuffer = AttributeKey.valueOf("dataBuffer");

    /**
     * 有客户端连接服务器会触发此函数
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // socke地址
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();

        Attribute<StringBuffer> attrBuffer = ctx.attr(dataBuffer);
        attrBuffer.set(new StringBuffer());

        log.info("netty7001客户端【" + insocket.getAddress().getHostAddress() + ":" + insocket.getPort() + "】建立连接。");
    }

    /**
     * 有客户端终止连接服务器会触发此函数
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("netty7001客户端退出【" + insocket.getAddress().getHostAddress() + ":" + insocket.getPort() + "】。");
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)  {

        //通道缓存，将拆包的字符串拼接起来
        /*Attribute<StringBuffer> attrBuffer = ctx.attr(dataBuffer);
        StringBuffer dataBuf = attrBuffer.get();
        dataBuf.append(msg);*/
        log.info("服务端接收到了==> {}", msg);
        ChannelMap.addChannel("1", ctx.channel());
        ctx.writeAndFlush("send:"+msg + "====success!");
    }
}
