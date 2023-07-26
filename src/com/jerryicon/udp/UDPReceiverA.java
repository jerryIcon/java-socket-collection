package com.jerryicon.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 接收端A
 * @author JerryIcon
 * @date 2023/7/26 14:30
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        // 1.在 9999 端口等待
        DatagramSocket socket = new DatagramSocket(9999);

        // 2.构建DatagramPacket
        // udp 协议数据包最大 64K
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        // 3.接收数据,填充到 packet
        // 如何没有数据包发送到 9999 端口 就会阻塞等待。
        System.out.println("等待接收数据。。。");
        socket.receive(packet);

        // 4.把packet 拆包 取出数据
        int length = packet.getLength();// 实际接收到的数据 字节长度
        byte[] data = packet.getData();
        String receive = new String(data, 0, length);
        System.out.println("接收到数据：" + receive);

        // 5.发送 "好的，明天见"
        byte[] bytes = "好的，明天见".getBytes();
        DatagramPacket packet1 = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 9998);
        socket.send(packet1);

        System.out.println("A端退出");
        socket.close();
    }
}
