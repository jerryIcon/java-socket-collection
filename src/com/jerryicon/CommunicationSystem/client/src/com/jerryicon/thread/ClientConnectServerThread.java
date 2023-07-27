package com.jerryicon.thread;

import com.jerryicon.common.Message;
import com.jerryicon.common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/27 15:39
 */
public class ClientConnectServerThread extends Thread {

    private Socket socket;

    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 如何服务器没有发生Message对象 线程会阻塞在这里
                Message message =(Message) ois.readObject();
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    // 收到 在线用户列表数据

                    // getContent:id1 id2 id3 ....
                    String[] onLineUsers = message.getContent().split(" ");
                    System.out.println("当前在线用户列表：(" + onLineUsers.length + ")");
                    if(onLineUsers != null && onLineUsers.length > 0){
                        for (int i = 0; i < onLineUsers.length; i++) {
                            System.out.println("用户：" + onLineUsers[i]);
                        }
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
