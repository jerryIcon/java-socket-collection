package com.jerryicon.homework.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author JerryIcon
 * @date 2023/7/26 15:44
 */
public class Homework02SenderB {
    public static void main(String[] args) throws IOException {
        /**
         *  (1)编写一个接收端A,和一个发送端B， 使用UDP协议完成
         *  (2)接收端在 8888端口等待接收数据(receive)
         *  (3)发送端向接收端 发送 数据 "四大名著是哪些"
         *  (4) 接收端接收到 发送端发送的 问题后，返回 "四大名著是<<红楼梦>>" 否则返回 what?
         *  (5) 接收端和发送端程序退出
         */
        // (1) 等待 8888 端口接收数据
        DatagramSocket socket = new DatagramSocket(8889);

        // (2) 发送 数据 "四大名著是哪些"
        System.out.println("发送数据：四大名著是哪些");
        byte[] bytes = "四大名著是哪些".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getLocalHost(), 8888);
        socket.send(packet);

        // （3）接收数据
        byte[] bytes1 = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(bytes1, 0, bytes1.length, InetAddress.getLocalHost(), 8888);
        socket.receive(packet1);
        String receive = new String(packet1.getData(), 0, packet1.getLength());
        System.out.println("接收到数据：" + receive);

        socket.close();
    }
}
