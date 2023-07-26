package com.jerryicon.upload;


import util.StreamUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/25 20:04
 */
public class SocketTcp03Server {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocket并监听指定端口
        ServerSocket serverSocket = new ServerSocket(8888);

        // 接收客户端连接
        Socket socket = serverSocket.accept();

        // 获取二进制图片
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = StreamUtil.streamToByteArray(inputStream);
        bufferedInputStream.close();

        // 写入文件
        String filePath = "E:\\project\\MyCollection\\socket\\src\\logo.png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(bytes);
        bos.close();

        // 发送收到图片的响应
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        outputStreamWriter.write("收到图片");
        outputStreamWriter.flush();
        socket.shutdownOutput();

        // 关闭流和Socket连接
        outputStreamWriter.close();
        socket.close();
        serverSocket.close();

    }
}
