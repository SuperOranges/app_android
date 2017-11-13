package com.sun.pd_mvp_clean.data.source.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountDataSource;

/**
 * Created by 孙毅 on 2017/11/13.
 */

public class FakeAccoutRemoteDataSource implements AccountDataSource {
    private  static FakeAccoutRemoteDataSource INTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;
    private FakeAccoutRemoteDataSource(){}

    public static final FakeAccoutRemoteDataSource getIntance(){
        if(INTANCE == null){
            INTANCE = new FakeAccoutRemoteDataSource();
        }
        return  INTANCE;
    }

    @Override
    public void findUser(@NonNull LoadUserCallback callback) {
        //不需要在远程数据源中实现
    }

    @Override
    public void verifyUser(@NonNull User user, final @NonNull LoadUserCallback callback) {
        //以下为测试的虚假数据和业务逻辑
        if (user.isFirstLogin() == true) {
            if (user.getUserId().equals("070709") && user.getPassword().equals("070709")) {
                final User newUser = new User("070709", "070709", "张三", false);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callback.onUserLoaded(newUser);
                    }
                }, SERVICE_LATENCY_IN_MILLIS);
            }else{
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callback.onDataNotAvailable();
                    }
                }, SERVICE_LATENCY_IN_MILLIS);
            }


        }else{
            if (user.getUserId().equals("070709") && user.getPassword().equals("070709")) {
                final User newUser = user;
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callback.onUserLoaded(newUser);
                    }
                }, SERVICE_LATENCY_IN_MILLIS);
            }else{
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callback.onDataNotAvailable();
                    }
                }, SERVICE_LATENCY_IN_MILLIS);
            }
        }
    }

    @Override
    public void saveUser(@NonNull User user) {
        //不需要在远程数据源中实现
    }

    @Override
    public void deleteUser() {
        //不需要在远程数据源中实现
    }
}
