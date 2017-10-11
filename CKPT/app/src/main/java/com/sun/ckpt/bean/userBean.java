package com.sun.ckpt.bean;

/**
 * Created by 孙毅 on 2017/10/5.
 */

public class userBean {


    private String userID;//员工编号即用户名
    private String password;//员工密码
    private String userName;//员工姓名
    private String userPosition;//员工岗位
    private int state;//一个状态来保存是否已登录

    //set method
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
    public void setState(int state) {
        this.state = state;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //get method
    public String getUserID() {
        return userID;
    }

    public String getUserPosition() {
        return userPosition;
    }
    public int getState() {
        return state;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
