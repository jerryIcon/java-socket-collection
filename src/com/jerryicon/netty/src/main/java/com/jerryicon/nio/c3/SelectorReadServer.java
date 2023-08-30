package com.jerryicon.nio.c3;

import com.jerryicon.nio.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author JerryIcon
 * @date 2023/8/23 21:06
 */
@Slf4j
public class SelectorReadServer {
    public static void main(String[] args) {

        try (ServerSocketChannel channel = ServerSocketChannel.open()){
            channel.bind(new InetSocketAddress(8080));
            channel.configureBlocking(false);

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                // 阻塞
                int count = selector.select();
                log.debug("select count: {}", count);

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()){
                        // 连接类型
                        ServerSocketChannel ssc =(ServerSocketChannel) key.channel();
                        // 连接
                        SocketChannel sc = ssc.accept();
                        log.debug("连接已建立: {}", sc);
                        sc.configureBlocking(false);
                        // 注册读事件
                        sc.register(selector, SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        int read = sc.read(buffer);
                        if(read == -1){
                            // 取消连接
                            key.cancel();
                            sc.close();
                        }else{
                            buffer.flip();
                            ByteBufferUtil.debugAll(buffer);
                        }
                    }

                    iterator.remove();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
