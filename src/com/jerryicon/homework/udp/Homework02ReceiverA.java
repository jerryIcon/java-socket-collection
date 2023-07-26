package com.jerryicon.homework.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author JerryIcon
 * @date 2023/7/26 15:44
 */
public class Homework02ReceiverA {
    public static void main(String[] args) throws IOException {
        /**
         *  (1)编写一个接收端A,和一个发送端B， 使用UDP协议完成
         *  (2)接收端在 8888端口等待接收数据(receive)
         *  (3)发送端向接收端 发送 数据 "四大名著是哪些"
         *  (4) 接收端接收到 发送端发送的 问题后，返回 "四大名著是<<红楼梦>>" 否则返回 what?
         *  (5) 接收端和发送端程序退出
         */
        // 1)编写一个接收端A 等待 8888 端口接收数据
        DatagramSocket socket = new DatagramSocket(8888);

        // 接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes,0, bytes.length);
        socket.receive(packet);
        byte[] data = packet.getData();
        String receive = new String(data, 0, packet.getLength());
        System.out.println("接收到数据：" + receive);

        if("四大名著是哪些".equals(receive)){
            // 发送 "四大名著是<<红楼梦>>"
            byte[] bytes1 = "四大名著是<<红楼梦>>".getBytes();
            DatagramPacket packet1 = new DatagramPacket(bytes1, 0, bytes1.length,InetAddress.getLocalHost(),  8889);
            socket.send(packet1);
        }else{
            // 发送 "what?"
            byte[] bytes1 = "what".getBytes();
            DatagramPacket packet1 = new DatagramPacket(bytes1, 0, bytes1.length,InetAddress.getLocalHost(),  8889);
            socket.send(packet1);
        }

        socket.close();
    }
}
