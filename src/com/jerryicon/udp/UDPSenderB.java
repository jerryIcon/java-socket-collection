package com.jerryicon.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 发送端B
 * @author JerryIcon
 * @date 2023/7/26 14:30
 */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        // 1.创建 DatagramSocket 准备发送和接收数据
        DatagramSocket socket = new DatagramSocket(9998);

        // 2. 将需要发送的数据 封装到 DatagramPacket
        System.out.println("发送数据。。。");
        byte[] data = "hello，明天吃火锅".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9999);

        // 3.发送
        socket.send(packet);

        // 4.阻塞等待接收数据
        byte[] receiveByte = new byte[1024];
        // 接收数据,填充到 packet1
        DatagramPacket packet1 = new DatagramPacket(receiveByte, receiveByte.length);
        socket.receive(packet1);
        // 5.把packet 拆包 取出数据
        System.out.println("接收到数据：" + new String(packet1.getData(),0, packet1.getLength()));

        System.out.println("B端退出");
        socket.close();
    }
}
