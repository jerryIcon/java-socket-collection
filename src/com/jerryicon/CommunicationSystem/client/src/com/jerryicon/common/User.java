package com.jerryicon.common;

import java.io.Serializable;

/**
 * @author JerryIcon
 * @date 2023/7/27 14:26
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 密码
     */
    private String passwd;

    public User(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
