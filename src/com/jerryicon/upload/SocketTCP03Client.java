package com.jerryicon.upload;

import util.StreamUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/25 19:46
 */
public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {

        // 创建Socket连接到服务器
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // 读取图片内容
        String filePath = "D:\\Backup\\Documents\\Pictures\\logo.png";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        // 读取文件内容对应的字节数组
        byte[] bytes = StreamUtil.streamToByteArray(bis);
        bis.close();

        // 发送字节数组
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();

        // 关闭输出流，表示数据发送完毕
        socket.shutdownOutput();

        // 接收服务端信息
        InputStream inputStream = socket.getInputStream();
        String serverResponse = StreamUtil.streamToString(inputStream);
        System.out.println("服务端响应：" + serverResponse);

        // 关闭流和Socket连接
        inputStream.close();
        bufferedOutputStream.close();
        outputStream.close();
        socket.close();

    }
}
