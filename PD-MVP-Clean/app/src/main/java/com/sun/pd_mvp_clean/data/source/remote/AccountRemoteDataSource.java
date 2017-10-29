package com.sun.pd_mvp_clean.data.source.remote;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 孙毅 on 2017/10/17.
 */

public class AccountRemoteDataSource implements AccountDataSource {

    private  static AccountRemoteDataSource INTANCE;

    private AccountRemoteDataSource(){}

    public static final AccountRemoteDataSource getIntance(){
        if(INTANCE == null){
            INTANCE = new AccountRemoteDataSource();
        }
        return  INTANCE;
    }

    @Override
    public void findUser(@NonNull LoadUserCallback callback) {
        //不需要在远程数据源中实现
    }

    @Override
    public void verifyUser(@NonNull User user, @NonNull LoadUserCallback callback) {

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
