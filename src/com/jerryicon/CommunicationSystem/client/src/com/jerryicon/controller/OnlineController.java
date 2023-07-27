package com.jerryicon.controller;

import com.jerryicon.common.User;
import com.jerryicon.server.OnlineServer;

/**
 * @author JerryIcon
 * @date 2023/7/27 19:35
 */
public class OnlineController {
    private OnlineServer onlineServer = new OnlineServer();

    /**
     * 请求获取在用户列表
     */
    public void requireOnline(User user){
        onlineServer.onlineFriendList(user);
    }
}
