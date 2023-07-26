package com.jerryicon.homework.download;

import util.StreamUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务的
 */
public class Homework03Server {
    public static void main(String[] args) throws IOException {
        // 开启 8888 端口
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();

        // 1.接收客户端的 文件名称
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        String fileName = "";
        while ((len = inputStream.read(b)) != -1){
            fileName += new String(b,0,len);
        }
        // 文件名称
        System.out.println("接受到文件名称：" + fileName);
        String filePath = "";
        if("高山流水".equals(fileName)){
            // 3.给客户端返回音乐
            filePath  = "E:\\practice\\java-socket-collection\\src\\com\\jerryicon\\homework\\download\\高山流水.mp3";
        }
        // 4. 读取 "高山流水.mp3"
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        byte[] bytes = StreamUtil.streamToByteArray(bis);

        // 5.将bytes 发生给客户端
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
        socket.shutdownInput();

        outputStream.close();
        bis.close();
        inputStream.close();
        socket.close();
    }
}
