package com.jerryicon.common;
/**
 * @author JerryIcon
 * @date 2023/7/27 14:30
 */
public interface MessageType {

    /**
     * 登录成功
     */
    String MESSAGE_LOGIN_SUCCEED = "1";

    /**
     * 登录失败
     */

    /**
     * 普通信息包
     */
    String MESSAGE_COMM_MES = "3";


    /**
     * 要求返回在线用户列表
     */
    String MESSAGE_GET_ONLINE_FRIEND = "4";

    /**
     * 返回在线用户列表
     */
    String MESSAGE_RET_ONLINE_FRIEND = "5";

    /**
     * 客户端请求退出
     */
    String MESSAGE_CLIENT_EXIT = "6";


}
