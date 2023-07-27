package com.jerryicon.thread;

import com.jerryicon.common.User;

import java.util.HashMap;

/**
 * @author JerryIcon
 * @date 2023/7/27 15:46
 */
public class ManageClientConnectServerThread {

    private static ManageClientConnectServerThread uniqueInstance;

    private HashMap<String, ClientConnectServerThread> hs = new HashMap<>();

    public static ManageClientConnectServerThread getUniqueInstance(){
        if (uniqueInstance == null) {
            uniqueInstance = new ManageClientConnectServerThread();
        }
        return uniqueInstance;
    }

    /**
     * 添加线程
     * @param user
     * @param ct
     */
    public void addThread(User user, ClientConnectServerThread ct){
        hs.put(user.getUserId(), ct);
    }

    /**
     * 获取线程
     * @param user
     */
    public ClientConnectServerThread getThread(User user){
        return hs.get(user.getUserId());
    }

    /**
     * 移除线程
     * @param user
     */
    public void removeThread(User user){
        hs.remove(user.getUserId());
    }
}
