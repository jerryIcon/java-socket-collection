package com.jerryicon.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/3 11:21
 * 服务端,发送"hello,client" 使用字符流
 */
public class SocketTCP02Server {
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

        // 4.IO读取 使用字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println("接收客户端" + s);


        // 5.发送数据
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,client 字符流");
        bufferedWriter.newLine(); // 插入换行付，表示结束
        bufferedWriter.flush(); // 使用字符流，需要手动刷新，否则不会写入通道
        // 结束标记
//        socket.shutdownOutput();

        // 5.关闭流和socket
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端关闭");
    }
}
