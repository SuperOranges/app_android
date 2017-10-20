package com.sun.pd_mvp_clean.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.account.domain.usecase.GetUser;

import java.util.List;

/**
 * Created by 孙毅 on 2017/10/17.
 */

public interface AccountDataSource {

    //取得服务器上下载的user
    interface LoadUserCallback{
        void onUserLoaded(User newUser);
        void onDataNotAvailable();
    }

    interface GetUserCallback{
        void onUserLoaded(User user);
        void onDataNotAvailable();
    }

    //取得新验证的User
    void getNewUser(@NonNull LoadUserCallback callback);
    //根据用户ID取得User
    void getUser(@NonNull String userID , @NonNull GetUserCallback callback);



    //把已登录的用户保存在本地
    void saveUser(@NonNull User user);
    //注销用户时删除本地的用户数据
    void deleteUser(@NonNull String userId);




}
