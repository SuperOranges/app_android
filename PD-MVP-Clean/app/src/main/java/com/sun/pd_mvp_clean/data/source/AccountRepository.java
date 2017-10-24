package com.sun.pd_mvp_clean.data.source;

import android.support.annotation.NonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import com.sun.pd_mvp_clean.account.domain.model.User;

/**
 * Created by 孙毅 on 2017/10/17.
 */

public class AccountRepository implements AccountDataSource {


    private static AccountRepository INSTANCE = null;
    private final AccountDataSource mAccountRemoteDataSource ;
    private  final AccountDataSource mAccountLocalDataSource;

    User mCacheUser;

    private AccountRepository(@NonNull AccountDataSource accountRemoteDataSource,@NonNull AccountDataSource accountLocalDataSource){
        mAccountRemoteDataSource  = checkNotNull(accountRemoteDataSource);
        mAccountLocalDataSource = checkNotNull(accountLocalDataSource);
    }

    public static  AccountRepository getINSTANCE(AccountDataSource accountRemoteDataSource,AccountDataSource accountLocalDataSource){
        if(INSTANCE == null){
            INSTANCE = new AccountRepository(accountRemoteDataSource,accountLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    }



    @Override
    public void getNewUser(@NonNull LoadUserCallback callback) {
            checkNotNull(callback);
            getUserDataFromRemoteSource(callback);
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

    private void getUserDataFromRemoteSource(final LoadUserCallback callback){
            mAccountRemoteDataSource.getNewUser(new LoadUserCallback() {
                @Override
                public void onUserLoaded(User newUser) {
                        refreshUser(newUser);
                        callback.onUserLoaded(mCacheUser);
                }

                @Override
                public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                }
            });
    }
    private  void refreshUser(User user){
        mCacheUser = user;
    }

}
