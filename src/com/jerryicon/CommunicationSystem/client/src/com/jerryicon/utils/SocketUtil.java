package com.jerryicon.utils;

import com.jerryicon.common.Message;
import com.jerryicon.common.User;
import com.jerryicon.thread.ManageClientConnectServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/27 20:01
 */
public class SocketUtil {

    /**
     * 发送Message
     * @param socket
     * @param message
     * @throws IOException
     */
    public static void sendMessage(Socket socket, Message message) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(message);
    }

    /**
     * 发送Message
     * @param user
     * @param message
     * @throws IOException
     */
    public static void sendMessage(User user, Message message) throws IOException {
        Socket socket = ManageClientConnectServerThread.getUniqueInstance().getThread(user).getSocket();
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(message);
    }

    /**
     * 接收Socket 信息
     * @param socket
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Message getMessage(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        return (Message) inputStream.readObject();
    }
}
