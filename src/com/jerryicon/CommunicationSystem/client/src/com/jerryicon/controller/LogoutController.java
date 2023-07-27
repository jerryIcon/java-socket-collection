package com.jerryicon.controller;

import com.jerryicon.common.User;
import com.jerryicon.server.LoginServer;
import com.jerryicon.server.LogoutServer;

/**
 * @author JerryIcon
 * @date 2023/7/27 20:29
 */
public class LogoutController {

    private LogoutServer logoutServer = new LogoutServer();

    /**
     * 退出系统
     */
    public void logout(User user){
        logoutServer.logout(user);
    }
}
