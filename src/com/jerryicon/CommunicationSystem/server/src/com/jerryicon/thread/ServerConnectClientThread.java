package com.jerryicon.thread;

import com.jerryicon.common.Message;
import com.jerryicon.common.MessageType;
import com.jerryicon.common.User;
import com.jerryicon.server.OnlineServer;
import com.jerryicon.utils.SocketUtil;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/27 16:27
 *
 */
public class ServerConnectClientThread extends Thread {

    /**
     * 当前客户端会话线程
     */
    private Socket socket;

    /**
     * 当前客户端用户
     */
    private User user;

    private OnlineServer onlineServer = new OnlineServer();

    public ServerConnectClientThread(User user,Socket socket){
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() {
        while (true){
            try {
                // 接收 客户端回传信息
                Message message = SocketUtil.getMessage(socket);

                // 客户端请求类型
                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
                    // 客户端请求：获取在线用户列表
                    System.out.println(message.getSender() + "请求获取 在线用户列表");

                    String onlineUser = onlineServer.getOnlineUser();
                    // 返回message 返回给客户端
                    SocketUtil.sendMessage(socket, new Message(message.getSender(),onlineUser,MessageType.MESSAGE_RET_ONLINE_FRIEND));
                } else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    // 客户端请求：退出系统
                    System.out.println(message.getSender() + " 退出系统");
                    // 将客户端对应的线程 从集合中移除
                    ManageClientThreads.getUniqueInstance().removeThread(message.getSender());
                    // 关闭socket
                    socket.close();
                    // 退出线程
                    break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
