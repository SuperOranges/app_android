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

    //判断曾经是否登陆过
    private  final boolean hasLogin;

    //新用户请求登录
    public User(@NonNull String userId, @NonNull String password) {
        this(userId,password, "try to login",false);
    }

    //服务器返回的带有所有数据的User对象或者保存在本地数据源的User对象
    public User(@NonNull String mUserId, @NonNull String mPassword,@Nullable String mUserName, boolean hasLogin) {
        this.mUserId = mUserId;
        this.mPassword = mPassword;
        this.mUserName = mUserName;
        this.hasLogin = hasLogin;
    }

    @NonNull
    public String getUserId() {
        return mUserId;
    }

    @NonNull
    public String getPassword() {

        return mPassword;
    }

    @Nullable
    public String getUserName() {
        return mUserName;
    }

    public boolean isHasLogin() {
        return hasLogin;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mUserId) &&
                Strings.isNullOrEmpty(mPassword);
    }



}
