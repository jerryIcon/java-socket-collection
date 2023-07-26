package com.jerryicon.homework.download;

import util.StreamUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端
 */
public class Homework03Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),8888);
        System.out.print("请输入文件名称：");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();

        // 1.向服务的发生音乐名称
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(fileName.getBytes());
        socket.shutdownOutput();

        // 2.接收服务的文件
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = StreamUtil.streamToByteArray(inputStream);

        // 3.保存文件
        String downFile = "E:\\practice\\java-socket-collection\\src\\" + "高山流水.mp3";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downFile));
        bos.write(bytes);

        bos.close();
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
