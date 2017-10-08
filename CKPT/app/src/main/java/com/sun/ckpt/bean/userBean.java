package com.sun.ckpt.bean;

/**
 * Created by 孙毅 on 2017/10/5.
 */

public class userBean {
    private String userName;
    private String password;
    private int state;//一个状态来保存是否已登录

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
