package com.sun.pd_mvp_clean.account.domain.usecase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/11/9.
 */

public class SaveUser extends UseCase<SaveUser.RequestValues,SaveUser.ResponseValue> {

    private final AccountRepository mAccountRepository;

    public SaveUser (@NonNull AccountRepository accountRepository){
        mAccountRepository = checkNotNull(accountRepository,"AccountRepository can not be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        User user = requestValues.getUser();
        mAccountRepository.saveUser(user);
        getUseCaseCallback().onSuccess(new ResponseValue(user));
    }

    public static class RequestValues implements UseCase.RequestValues {


        private final User mUser;
        public User getUser() {
            return mUser;
        }
        public RequestValues (@NonNull User user){
            mUser = checkNotNull(user,"user can not be null");
            Log.e("SaveUserRequest",mUser.getUserId());
        }

    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final User mUser;
        public User getUser() {
            return mUser;
        }
        public ResponseValue (@NonNull User user){
            mUser = checkNotNull(user,"user can not be null");
            Log.e("SaveUserResponse",mUser.getUserId());
        }
    }

}
