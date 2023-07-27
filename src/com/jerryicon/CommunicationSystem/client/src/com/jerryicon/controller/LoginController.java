package com.jerryicon.controller;


import com.jerryicon.common.User;
import com.jerryicon.server.LoginServer;

/**
 * @author JerryIcon
 * @date 2023/7/27 15:21
 */
public class LoginController {

    private LoginServer loginServer = new LoginServer();


    public boolean login(User user){
        return loginServer.login(user);
    }
}
