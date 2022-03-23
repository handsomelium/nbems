package com.ake.nbems.eaps.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @description: 服务端初始化，客户端与服务器端连接一旦创建，这个类中方法就会被回调，设置出站编码器和入站解码器
 **/

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
    	//ChannelPipeline pipeline = channel.pipeline();
        channel.pipeline().addLast(new MyMessageEncoder());//加入编码器
        channel.pipeline().addLast(new MyMessageDecoder());//加入解码器
//        channel.pipeline().addLast(new OldMyMessageDecoder());//加入解码器
        channel.pipeline().addLast(new NettyServerHandeler());
    }
}
