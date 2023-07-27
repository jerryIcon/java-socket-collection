package com.jerryicon.server;

import com.jerryicon.common.Message;
import com.jerryicon.common.MessageType;
import com.jerryicon.common.User;
import com.jerryicon.thread.ClientConnectServerThread;
import com.jerryicon.thread.ManageClientConnectServerThread;
import com.jerryicon.utils.SocketUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * @author JerryIcon
 * @date 2023/7/27 20:31
 */
public class LogoutServer {
    /**
     * 退出系统
     * @param user
     */
    public void logout(User user){
        // 退休系统标识
        Message message = new Message(user.getUserId(), MessageType.MESSAGE_CLIENT_EXIT);
        try {
            // 发送Message
            SocketUtil.sendMessage(user, message);
            System.out.println(user.getUserId() + " 退出系统");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
