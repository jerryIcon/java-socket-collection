package com.jerryicon.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件读取
 * @author JerryIcon
 * @date 2023/7/31 14:56
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel 文件数据读写通道
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()){
            // 1.准备缓冲区 capacity:缓存区大小（字节）
            ByteBuffer buffer = ByteBuffer.allocate(10);
            int len = 0;
            while (len != -1){
                // 2.从 channel 读取数据，向buffer写入; len == -1 则标识没有内容
                len = channel.read(buffer);
                log.debug("读取到的字节数 {}",len);
                // 3.切换至读模式
                buffer.flip();
                // buffer.hasRemaining() 是否还有剩余未读的数据
                while (buffer.hasRemaining()){
                    // 4.从buffer读取数据 get() 读取一个字节
                    byte b = buffer.get();
                    log.debug("实际字节 {}",(char) b);
                }
                // 5.切换至写模式
                buffer.clear();
            }
        } catch (IOException e) {
        }
    }
}
