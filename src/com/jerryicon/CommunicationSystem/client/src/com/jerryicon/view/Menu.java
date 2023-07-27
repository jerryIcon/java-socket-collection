package com.jerryicon.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author JerryIcon
 * @date 2023/7/27 14:46
 * 菜单
 */
public class Menu {

    private Map<Integer,String> menu;

    /**
     * 初始化菜单
     * @param menu
     */
    public  Menu(Map<Integer,String> menu){
        this.menu = menu;
    }

    public Menu(){
    }

    public void view(){
        Set<Map.Entry<Integer, String>> entries =
                menu.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
