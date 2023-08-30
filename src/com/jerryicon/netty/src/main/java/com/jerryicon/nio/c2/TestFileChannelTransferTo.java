package com.jerryicon.nio.c2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author JerryIcon
 * @date 2023/8/23 8:54
 */
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel FROM = new FileInputStream("data.txt").getChannel();
             FileChannel TO = new FileInputStream("to.txt").getChannel()){
            long size = FROM.size();
            for (long left = size; left > 0;){
                // 本次开始位置
                long start = size - left;
                left -= FROM.transferTo(start, left, TO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
