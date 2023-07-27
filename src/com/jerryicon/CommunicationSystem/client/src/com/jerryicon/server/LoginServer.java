package com.jerryicon.server;

import com.jerryicon.common.Message;
import com.jerryicon.common.MessageType;
import com.jerryicon.common.User;
import com.jerryicon.thread.ClientConnectServerThread;
import com.jerryicon.thread.ManageClientConnectServerThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/27 15:58
 */
public class LoginServer {
    /**
     * 登录
     * @param user 用户信息
     * @return boolean
     */
    public boolean login(User user) {
        try {
            // 1. 创建socket
            Socket socket = new Socket(InetAddress.getLocalHost(),9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // 2.写入
            oos.writeObject(user);
            // 3.读取服务器返回消息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            if(MessageType.MESSAGE_LOGIN_SUCCEED.equals(message.getMesType())){
                // 登录成功
                // 启动客户端连接线程
                ClientConnectServerThread serverThread = new ClientConnectServerThread(socket);
                serverThread.start();
                // 加入线程池
                ManageClientConnectServerThread manageThread = ManageClientConnectServerThread.getUniqueInstance();
                manageThread.addThread(user, serverThread);
                return true;
            }else{
                // 登录失败
                socket.close();
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
