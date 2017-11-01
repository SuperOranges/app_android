package com.sun.pd_mvp_clean.data.source;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.account.domain.model.User;

/**
 * Created by 孙毅 on 2017/10/17.
 */

public interface AccountDataSource {

    //取得服务器上下载的user或本地数据库中找到的User
    interface LoadUserCallback{
        void onUserLoaded(User newUser);
        void onDataNotAvailable();
    }

    /**该方法从数据库中去寻找用户信息
     *
    */
    void findUser(@NonNull  LoadUserCallback callback);

    /**这个方法是将取得的用户信息上传至服务器验证的方法
     *
     * 传入的参数来自于findUser()方法返回的User信息，或者用户自行输入的包装好的User对象
     */
    void verifyUser(@NonNull User user,@NonNull LoadUserCallback callback);


    /**这个方法是把服务器验证过得用户信息保存在本地数据库的方法
     *传入的参数来自于verifyUser()方法返回的User
     *
     */
    void saveUser(@NonNull User user);

    /**这个方法是注销用户时删除本地的用户数据
     *删除全部的用户数据，仅仅允许记住一个用户
     */
    void deleteUser();


}
