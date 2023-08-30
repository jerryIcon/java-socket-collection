package com.jerryicon.nio.c2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author JerryIcon
 * @date 2023/8/23 8:29
 */
public class ChannelTest1 {
    public static void main(String[] args){
        String FROM = "data.txt";
        String TO = "to.txt";
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
        FileChannel to = new FileOutputStream(TO).getChannel()){
            from.transferTo(0, from.size(), to);
        }catch (IOException e){
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("用时：" + (end - start) / 1000000);
    }
}
