package com.jerryicon.view;


import com.jerryicon.controller.LogoutController;
import com.jerryicon.utils.ReadUtility;
import com.jerryicon.common.User;
import com.jerryicon.controller.LoginController;
import com.jerryicon.controller.OnlineController;

import java.util.HashMap;

/**
 * @author JerryIcon
 * @date 2023/7/27 14:42
 */
public class View {

    private boolean loop = true;

    private LoginController loginController = new LoginController();

    private OnlineController onlineController = new OnlineController();

    private LogoutController logoutController = new LogoutController();



    /**
     * 登录页面
     */
    private void head(String title){
        System.out.println("=============" + title + "=============");
    }

    /**
     * 登录页面
     */
    public Menu loginView(){
        this.head("欢迎登录网络通信系统");
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"登录系统");
        map.put(9,"退出系统");
        return new Menu(map);
    }

    public Menu menuView(User user){
        this.head("网络通信系统二级菜单(用户：" + user.getUserId() + ")");
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"显示在线用户列表");
        map.put(2,"群发消息");
        map.put(3,"私聊消息");
        map.put(4,"发送文件");
        map.put(9,"退出系统");
        return new Menu(map);
    }

    public void main(){

        while (loop){
            // 登录菜单
            Menu loginView = loginView();
            loginView.view();
            System.out.print("请输入操作：");
            // 用户输入控制消息key
            int readInt = ReadUtility.readInt();
            switch (readInt){
                case 1:
                    System.out.print("请输入用户名：");
                    String userId = ReadUtility.readString(50);
                    System.out.print("请输入密 码：");
                    String passwd = ReadUtility.readString(50);
                    User user = new User(userId, passwd);
                    if(loginController.login(user)){
                        while (loop){
                            // 登录成功
                            //  显示二级菜单
                            Menu menu = menuView(user);
                            menu.view();
                            System.out.print("请输入操作：");
                            // 用户输入控制消息key
                            readInt = ReadUtility.readInt();
                            switch (readInt){
                                case 1:
                                    // 请求获取用户在线列表
                                    onlineController.requireOnline(user);
                                    break;
                                case 9:
                                    // 退出系统
                                    logoutController.logout(user);
                                    loop = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }else{
                        this.head("账户名或密码错误");
                        // 登录失败
                        break;
                    }
                case 9:
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出系统成功！");
    }
}
