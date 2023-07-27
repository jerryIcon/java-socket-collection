package com.jerryicon.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JerryIcon
 * @date 2023/7/27 14:28
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发送者
     */
    private String sender;

    /**
     * 接收者
     */
    private String getter;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 消息类型
     */
    private String mesType;

    public Message(){}


    public Message( String getter, String content, String mesType) {
        this.getter = getter;
        this.content = content;
        this.mesType = mesType;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM hh:ss:mm");
        String format = simpleDateFormat.format(new Date());
        this.sendTime = format;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }
}
