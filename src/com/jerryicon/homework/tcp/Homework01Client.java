package com.jerryicon.homework.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author JerryIcon
 * @date 2023/7/26 14:55
 */
public class Homework01Client {
    public static void main(String[] args) throws IOException {
        // 客户端 使用字符流发送
        // 监听 9999 端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        // 1.客户端发送 "name"
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        System.out.println("发送数据：name");
        bufferedWriter.write("name");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 2.等待接收数据
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        System.out.println("接收到数据：" + line);


        // 3.客户端发送 “hobby”
        System.out.println("发送数据：hobby");
        bufferedWriter.write("hobby");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 4.接收数据
        String line1 = bufferedReader.readLine();
        System.out.println("接收到数据：" + line1);

        outputStream.close();
        inputStream.close();
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }
}
