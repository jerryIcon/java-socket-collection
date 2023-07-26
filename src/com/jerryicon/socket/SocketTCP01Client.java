package com.jerryicon.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author JerryIcon
 * @date 2023/7/3 11:33
 * 客户端,发送"hello,server"
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        // 1.连接服务器
        // 解读：连接本机 9999端口，如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket=" + socket.getClass());

        // 2.连接上后，生成Socket，通过 socket.getOutputStream()
        // 得到socket对象关联的OutputStream()
        OutputStream outputStream = socket.getOutputStream();

        // 3.通过输出流，写入数据到数据通道
        outputStream.write("hello,server".getBytes());
        // 结束标记
        socket.shutdownOutput();

        // 4.读取数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            System.out.println("收到服务端数据:" + new String(buf, 0, readLen));
        }

        // 5.关闭流socket
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
