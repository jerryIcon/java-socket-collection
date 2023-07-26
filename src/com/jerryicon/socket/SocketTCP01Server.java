package com.jerryicon.socket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/3 11:21
 * 服务端
 */
@SuppressWarnings("")
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        // 1.监听9999端口
        // serverSocket通过accept() 返回多个 Socket[多个客户端连接并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在9999端口监听，等待连接...");

        // 2.在客户端连接9999端口时，程序会阻塞，等待连接
        // 如何有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("socket=" + socket.getClass());

        // 3.通过socket.getInputStream()
        InputStream inputStream = socket.getInputStream();

        // 4.IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf)) != -1){
            System.out.println("收到客户端数据:" + new String(buf, 0, readLen)); // 根据读取到的实际长度，显示内容。
        }

        // 5.发送数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        // 结束标记
        socket.shutdownOutput();

        // 5.关闭流和socket
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端关闭");
    }
}
