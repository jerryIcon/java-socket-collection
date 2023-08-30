package com.jerryicon.nio.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author JerryIcon
 * @date 2023/8/23 20:46
 */
@Slf4j
public class SelectorAcceptServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        // 监听 Channel 事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int count = selector.select();
            log.debug("count:" + count);
            if(count <= 0){
                continue;
            }
            // 获取所有事件
            Set<SelectionKey> keys = selector.selectedKeys();

            // 遍历事件
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                // 判断事件类型
                if(key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel accept = channel.accept();
                    log.debug("建立连接...{}", accept);
                }
                iterator.remove();
            }
        }
    }
}
