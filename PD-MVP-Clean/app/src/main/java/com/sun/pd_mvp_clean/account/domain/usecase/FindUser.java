package com.sun.pd_mvp_clean.account.domain.usecase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountDataSource;
import com.sun.pd_mvp_clean.data.source.AccountRepository;
import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Administrator on 2017/10/29.
 *
 * 此类用于在欢迎页面时从本地数据库寻找曾经保存的用户信息然后返回用户信息
 */

public class FindUser extends UseCase<FindUser.RequestValues,FindUser.ResponseValue> {

    private final AccountRepository mAccountRepository;

    public FindUser(@NonNull AccountRepository accountRepository){
        mAccountRepository = checkNotNull(accountRepository,"AccountRepository can not be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.e("FindUserExecute","excute");
        mAccountRepository.findUser(new AccountDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(User newUser) {
                if (newUser != null) {
                    ResponseValue responseValue = new ResponseValue(newUser);

                    Log.e("FindUserExecute", newUser.getUserId());

                    getUseCaseCallback().onSuccess(responseValue);
                } else {
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
        public RequestValues(){}
    }

    public  static  final class ResponseValue implements UseCase.ResponseValue{
        private final User mUser;

        public User getUser() {
            return mUser;
        }

        public ResponseValue(@NonNull  User user){
            mUser = checkNotNull(user,"user can not be null");
            Log.e("FindUserResponse",mUser.getUserId());
        }
    }

}
