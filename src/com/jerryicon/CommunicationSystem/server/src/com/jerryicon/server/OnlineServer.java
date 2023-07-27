package com.jerryicon.server;

import com.jerryicon.thread.ManageClientThreads;
import com.jerryicon.thread.ServerConnectClientThread;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author JerryIcon
 * @date 2023/7/27 19:54
 */
public class OnlineServer {

    /**
     * 获取在线用户列表
     * @return
     */
    public String  getOnlineUser(){
        // 在线用户线程
        HashMap<String, ServerConnectClientThread> map = ManageClientThreads.hs;
        Iterator<String> iterator = map.keySet().iterator();
        String onlineUserList = "";
        while (iterator.hasNext()){
            onlineUserList += iterator.next().toString() + " ";
        }
        return onlineUserList;
    }
}
