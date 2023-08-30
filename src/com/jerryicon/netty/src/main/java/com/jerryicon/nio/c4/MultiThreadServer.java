package com.jerryicon.nio.c4;

import com.jerryicon.nio.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JerryIcon
 * @date 2023/8/27 17:46
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);

        // 1.创建固定数量worker
        Worker[] workers = new Worker[2];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger index = new AtomicInteger();
        while (true){
            boss.select();
            Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
            if (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if(key.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.debug("建立连接...{}", sc);
                    // 2.关联 selector
                    log.debug("before register...{}", sc);
                    // round robin 轮询
                    workers[index.getAndIncrement() % workers.length].register(sc);  // boss线程 初始化selector 启动worker-0
                    log.debug("after register...{}", sc);
                }
            }
        }
    }

    static class Worker implements Runnable{
        private Thread thread;
        private Selector selector;
        private String name;

        private volatile boolean start = false;

        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>(); // 线程任务队列

        public  Worker(String name){
            this.name = name;
        }

        // 初始化线程 和 selector
        public void register(SocketChannel sc) throws IOException {
            if(!start){
                thread = new Thread(this);
                thread.setName(name);
                selector = Selector.open();
                thread.start();
                start = true;
            }
            queue.add(()->{
                try {
                    // 将任务代码放到线程
                    sc.register(selector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });
            selector.wakeup();  //唤醒 select 方法
        }

        @Override
        public void run() {
            while (true){
                try {
                    selector.select();  // worker-0 阻塞，wakeup：唤醒阻塞
                    Runnable task = queue.poll();
                    if(task != null){
                        // 执行任务的代码 sc.register(selector, SelectionKey.OP_READ, null);
                        task.run();
                    }
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()){
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel)key.channel();
                            log.debug("read...{}", channel);
                            channel.read(buffer);
                            buffer.flip();
                            ByteBufferUtil.debugAll(buffer);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
