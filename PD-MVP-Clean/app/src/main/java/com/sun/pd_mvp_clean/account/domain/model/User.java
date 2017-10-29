package com.sun.pd_mvp_clean.account.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Created by Administrator on 2017/10/20.
 */

public class User {

    @NonNull
    private final String mUserId;
    @NonNull
    private final String mPassword;
    @Nullable
    private final String mUserName;

    //此变量用于判断是否是第一次登陆
    private  final boolean firstLogin;

    //新用户请求登录
    public User(@NonNull String userId, @NonNull String password) {
        this(userId,password ,null , true);
    }

    //服务器返回的带有所有数据的User对象或者保存在本地数据源的User对象
    public User(@NonNull String mUserId, @NonNull String mPassword,@Nullable String mUserName, boolean firstLogin) {
        this.mUserId = mUserId;
        this.mPassword = mPassword;
        this.mUserName = mUserName;
        this.firstLogin = firstLogin;
    }

    //  取得用户ID
    @NonNull
    public String getUserId() {
        return mUserId;
    }

    //  取得用户密码
    @NonNull
    public String getPassword() {
        return mPassword;
    }

    //  取得用户姓名
    @Nullable
    public String getUserName() {
        return mUserName;
    }

    //  判断是否是第一次登陆
    public boolean isFirstLogin() {
        return firstLogin;
    }

    //  这个User对象是否是空的
    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mUserId) &&
                Strings.isNullOrEmpty(mPassword);
    }



}
