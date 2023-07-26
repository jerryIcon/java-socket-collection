package com.jerryicon.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author JerryIcon
 * @date 2023/7/3 8:45
 * 演示INetAddress 类的使用
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {

        // 1.获取本机的InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        // 2.根据指定的主机名 获取InetAddress 对象
        InetAddress name = InetAddress.getByName("PC-202012101018");
        System.out.println(name);

        // 3.根据域名返回 获取InetAddress 对象， 比如www.baidu.com
        InetAddress baiduName = InetAddress.getByName("www.baidu.com");
        System.out.println("baiduName" + baiduName);

        // 4.通过InetAddress 对象，获取对应的地址
        String hostAddress = baiduName.getHostAddress();
        System.out.println(hostAddress);
    }
}
