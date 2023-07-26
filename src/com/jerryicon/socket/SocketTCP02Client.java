package com.jerryicon.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * @author JerryIcon
 * @date 2023/7/3 11:33
 * 客户端,发送"hello,server" 使用字符流
 */
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        // 1.连接服务器
        // 解读：连接本机 9999端口，如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket=" + socket.getClass());

        // 2.连接上后，生成Socket，通过 socket.getOutputStream()
        // 得到socket对象关联的OutputStream()
        OutputStream outputStream = socket.getOutputStream();

        // 3.通过输出流，写入数据到数据通道,使用字符流
        // OutputStreamWriter 将字节流转为字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,server 字符流");
        bufferedWriter.newLine(); // 插入换行付，表示结束
        bufferedWriter.flush(); // 使用字符流，需要手动刷新，否则不会写入通道
        // 结束标记
//        socket.shutdownOutput();

        // 4.读取数据
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println("接收服务端：" + s);

        // 5.关闭流socket
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
