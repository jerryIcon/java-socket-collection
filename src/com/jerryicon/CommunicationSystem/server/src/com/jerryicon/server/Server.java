package com.jerryicon.server;

import com.jerryicon.common.Message;
import com.jerryicon.common.MessageType;
import com.jerryicon.common.User;
import com.jerryicon.thread.ManageClientThreads;
import com.jerryicon.thread.ServerConnectClientThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
* @author JerryIcon
* @date 2023/7/27 16:20
*/public class Server {

    private static HashMap<String,User> users = new HashMap<>();

    static {
        users.put("100",new User("100","100"));
        users.put("jerry",new User("jerry","jerry"));
    }

    public Server(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
            while (true){
                // 接收用户信息
                Socket socket = serverSocket.accept();
                // 获取信息
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 获取用户信息
                User user =(User) ois.readObject();
                // 准备传输的message
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                // 校验用户信息
                if(this.checkUser(user)){
                    // 账号校验成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
                    // 加入线程
                    ServerConnectClientThread thread = new ServerConnectClientThread(user, socket);
                    // 启动线程
                    thread.start();
                    ManageClientThreads manageClientThreads = ManageClientThreads.getUniqueInstance();
                    manageClientThreads.addThread(user,thread);
                }else{
                    // 账号校验失败
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean checkUser(User user){
        User realUser = users.get(user.getUserId());

        if(realUser == null){
            System.out.println("账号不存在");
            return false;
        }

        if(!user.getPasswd().equals(realUser.getPasswd())){
            System.out.println("账号不存在");
            return false;
        }
        return true;

    }
}
