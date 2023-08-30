package com.jerryicon.nio.c1;

import com.jerryicon.nio.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @author JerryIcon
 * @date 2023/8/22 20:59
 */
public class TestByteBuffer1 {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source){
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if(source.get(i) == '\n'){
                // 匹配到 \n
                ByteBuffer target = ByteBuffer.allocate(i + 1);
                source.limit(i + 1);
                target.put(source);
                source.limit(oldLimit);
                ByteBufferUtil.debugAll(target);
            }
        }
        source.compact();
    }
}
