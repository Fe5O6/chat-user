package com.h.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty测试类
 */
public class NettyHello {
    public static void main(String[] args) {
        new ServerBootstrap()
                // BossEvenloop,WorkerEventloop(selector，thread)，group组
                .group(new NioEventLoopGroup())
                // 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // boss负责处理连接worker（child）负责处理读写，决定了worker（child）能执行那些操作（heandler）
                .childHandler(
                        // channel代表和客户端进行数据读写的通道 initializer初始化，负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        // 添加具体的handler
                        nioSocketChannel.pipeline().addLast(new StringDecoder()); //将bytebuf转换成字符串
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            // 读事件
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 读取发送的信息
                                System.out.println(msg);
                            }
                        });
                    }
                }).bind(8100); //设置访问服务器的端口号
    }
}
