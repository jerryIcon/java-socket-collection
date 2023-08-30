package com.jerryicon.nio.c3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author JerryIcon
 * @date 2023/8/23 20:20
 */
public class Client {
    public static void main(String[] args) throws IOException {
//        try (Socket socket = new Socket("localhost", 8080)) {
//            System.out.println(socket);
//            socket.getOutputStream().write("world".getBytes());
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8080));
        SocketAddress address = sc.getLocalAddress();
        // sc.write(Charset.defaultCharset().encode("hello\nworld\n"));
        sc.write(Charset.defaultCharset().encode("0123\n456789abcdef"));
        sc.write(Charset.defaultCharset().encode("0123456789abcdef3333\n"));
        System.in.read();
    }
}
