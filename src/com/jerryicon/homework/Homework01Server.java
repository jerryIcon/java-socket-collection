package com.jerryicon.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/26 14:55
 */
public class Homework01Server {
    public static void main(String[] args) throws IOException {
        // 服务端
        ServerSocket socket = new ServerSocket(9999);
        Socket accept = socket.accept();

        InputStream inputStream = accept.getInputStream();

        // 1.接收数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        System.out.println("接收数据：" + line);

        // 2.发送数据 "我是 nova"
        System.out.println("发送数据：我是 nova");
        OutputStream outputStream = accept.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("我是 nova");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 3.等待接收数据
        String line1 = bufferedReader.readLine();
        System.out.println("接收数据：" + line1);

        // 4.发送数据 "编写java程序"
        System.out.println("发送数据：编写java程序");
        bufferedWriter.write("编写java程序");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        outputStream.close();
        bufferedReader.close();
        inputStream.close();
        bufferedWriter.close();
        accept.close();
        socket.close();
    }
}
