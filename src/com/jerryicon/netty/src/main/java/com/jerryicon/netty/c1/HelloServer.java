package com.jerryicon.netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author JerryIcon
 * @date 2023/8/28 20:21
 */
public class HelloServer {
    public static void main(String[] args) {
        // 1.ServerBootstrap 服务端启动器，负责组装 netty 组件 启动服务
        new ServerBootstrap()
                // 2. BossEventLoop, WorkerEventLoop(selector, thread) group组
                .group(new NioEventLoopGroup())
                // 3. 选择 服务器 的基于ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                // 4. boss 负责处理连接, worker(child) 负责处理读写，决定了worker(child)能执行哪些操作 (handler)
                .childHandler(
                        // 5.NioSocketChannel和客户端进行数据的读写通道 Initializer 初始化，负责添加别的 handler
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 6. 添加具体handler 将ByteBuf 转为字符串
                        ch.pipeline().addLast(new StringDecoder());
                        // 7. 自定义handler
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            // 读事件
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8080);
    }
}
