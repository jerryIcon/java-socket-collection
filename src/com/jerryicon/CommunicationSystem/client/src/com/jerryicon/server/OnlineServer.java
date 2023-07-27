package com.jerryicon.server;

import com.jerryicon.common.Message;
import com.jerryicon.common.MessageType;
import com.jerryicon.common.User;
import com.jerryicon.thread.ClientConnectServerThread;
import com.jerryicon.thread.ManageClientConnectServerThread;
import com.jerryicon.utils.SocketUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/27 19:35
 */
public class OnlineServer {

    /**
     * 向服务端请求在线用户列表
     */
    public void onlineFriendList(User user){
        // 发送一个Message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());

        // 发送给服务器
        // 获取当前线程 Socket
        ManageClientConnectServerThread threadManage = ManageClientConnectServerThread.getUniqueInstance();
        Socket socket = threadManage.getThread(user).getSocket();
        try {
            // 发送Message 给服务器
            SocketUtil.sendMessage(socket, message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
