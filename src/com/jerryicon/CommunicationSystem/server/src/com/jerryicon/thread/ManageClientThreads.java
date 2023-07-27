package com.jerryicon.thread;

import com.jerryicon.common.User;

import java.util.HashMap;

/**
 * @author JerryIcon
 * @date 2023/7/27 16:27
 * 用于管理和客户端通信的线程
 */
public class ManageClientThreads {
    private static ManageClientThreads uniqueInstance;

    public static HashMap<String, ServerConnectClientThread> hs = new HashMap<>();

    public static ManageClientThreads getUniqueInstance(){
        if (uniqueInstance == null) {
            uniqueInstance = new ManageClientThreads();
        }
        return uniqueInstance;
    }

    /**
     * 添加线程
     * @param user
     * @param ct
     */
    public void addThread(User user, ServerConnectClientThread ct){
        hs.put(user.getUserId(), ct);
    }

    /**
     * 移除线程
     * @param userId
     */
    public void removeThread(String userId){
        hs.remove(userId);
    }


}
