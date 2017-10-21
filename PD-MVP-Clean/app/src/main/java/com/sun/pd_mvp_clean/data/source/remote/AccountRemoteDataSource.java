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
    private  static final Map<String, User> TASKS_SERVICE_DATA= new LinkedHashMap<>();

    private AccountRemoteDataSource(){}

    public static final AccountRemoteDataSource getINTANCE(){
        if(INTANCE == null){
            INTANCE = new AccountRemoteDataSource();
        }
        return  INTANCE;
    }
    @Override
    public void getNewUser(@NonNull LoadUserCallback callback) {

    }

    @Override
    public void getUser(@NonNull String userID, @NonNull GetUserCallback callback) {

    }

    @Override
    public void saveUser(@NonNull User user) {

    }

    @Override
    public void deleteUser(@NonNull String userId) {

    }
}
