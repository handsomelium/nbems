package com.ake.nbems.eaps.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author lium
 * @Date 2022/3/23
 * @Description
 */

@Slf4j
@Component
public class NettyServer {



    public void start(){
        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap1 = new ServerBootstrap()
                    .group(bossGroup, workerGroup)  // 绑定线程池
                    .channel(NioServerSocketChannel.class)
                    //.localAddress(address)
                    .childHandler(new NettyServerChannelInitializer())//编码解码
                    .option(ChannelOption.SO_BACKLOG, 1024)	//服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
                    .option(ChannelOption.TCP_NODELAY, true)	//禁用nagle算法，不等待，立即发送。
                    .childOption(ChannelOption.SO_KEEPALIVE, true)	//保持长连接，2小时无数据激活心跳机制
                    .childOption(ChannelOption.TCP_NODELAY, true);	//禁用nagle算法，不等待，立即发送。


            // 绑定端口，开始接收进来的连接
            ChannelFuture future1 = bootstrap1.bind(7001).sync();
            log.info("netty1服务器开始监听端口：" + 7001);
            //关闭channel和块，直到它被关闭
            future1.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
            log.error("netty服务器开启失败");
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }






}
