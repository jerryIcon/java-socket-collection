package com.jerryicon.netty.c2;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author JerryIcon
 * @date 2023/8/29 8:44
 */
@Slf4j
public class TestEventLoop {

    public static volatile int i = 0;

    public static void main(String[] args) {

        // 1.创建事件循环组
        EventLoopGroup group = new NioEventLoopGroup(2); // io 事件 普通任务 定时任务
//        DefaultEventLoopGroup eventExecutors = new DefaultEventLoopGroup();   // 普通任务，定时任务
//        System.out.println(NettyRuntime.availableProcessors());

        // 2.获取下一个事件循环对象
        System.out.println(group.next());
        // 3.执行普通任务
//        group.next().execute();
//        for (int j = 0; j < 100; j++) {
//            group.next().submit(()->{
//               i++;
//               log.debug(String.valueOf(i));
//            });
//        }
        // 4. 执行定时任务
        group.next().scheduleAtFixedRate(()->{
            log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS);
        log.debug("main");
    }
}
