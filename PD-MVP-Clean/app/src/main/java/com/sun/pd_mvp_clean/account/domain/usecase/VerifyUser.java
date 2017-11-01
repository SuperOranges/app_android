package com.sun.pd_mvp_clean.account.domain.usecase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountDataSource;
import com.sun.pd_mvp_clean.data.source.AccountRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/11/1.
 */

public class VerifyUser extends UseCase<VerifyUser.RequestValues,VerifyUser.ResponseValue>{

    private final AccountRepository mAccountRepository;
    public VerifyUser(@NonNull AccountRepository accountRepository){
        mAccountRepository = checkNotNull(accountRepository,"AccountRepository can not be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mAccountRepository.verifyUser(requestValues.getUser(), new AccountDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(User newUser) {

                if (newUser != null) {

                    ResponseValue responseValue = new ResponseValue(newUser);

                    Log.e("VerityUserExecute", newUser.getUserId());

                    getUseCaseCallback().onSuccess(responseValue);

                }else{

                    getUseCaseCallback().onError();

                }
            }

            @Override
            public void onDataNotAvailable() {

                getUseCaseCallback().onError();

            }
        });
    }

    public  static  final class RequestValues implements UseCase.RequestValues{

        private final User mUser;
        public RequestValues(@NonNull User user){
            mUser = user;
        }
        public User getUser() {
            return mUser;
        }
    }
    public  static  final class ResponseValue implements UseCase.ResponseValue {
        private final User mUser;

        public User getUser() {
            return mUser;
        }

        public ResponseValue(@NonNull  User user){
            mUser = checkNotNull(user,"user can not be null");
            Log.e("VerifyUserResponse",mUser.getUserId());
        }
    }
}

