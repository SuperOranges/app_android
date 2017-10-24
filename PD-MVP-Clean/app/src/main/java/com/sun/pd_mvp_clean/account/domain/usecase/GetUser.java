package com.sun.pd_mvp_clean.account.domain.usecase;

import static com.google.common.base.Preconditions.checkNotNull;
import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountDataSource;
import com.sun.pd_mvp_clean.data.source.AccountRepository;
import com.sun.pd_mvp_clean.tasks.domain.usecase.GetTasks;

/**
 * Created by Administrator on 2017/10/20.
 */

public class GetUser extends UseCase<GetUser.RequestValues,GetUser.ResponseValue>{


    private final AccountRepository mAccountRepository;



    public GetUser (@NonNull AccountRepository accountRepository){
            mAccountRepository = checkNotNull(accountRepository,"数据仓库不能是空的");
    }



    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mAccountRepository.getNewUser(new AccountDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(User newUser) {
                ResponseValue response = new ResponseValue(newUser);
                getUseCaseCallback().onSuccess(response);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }


    //把要请求的东西先准备在请求参数里
    public static final class RequestValues implements UseCase.RequestValues{

                //在请求参数里放个要登录的User
        private final User mLginingUser;

        public RequestValues(@NonNull User loginingUser){
                        mLginingUser =  checkNotNull(loginingUser,"想要登录的用户的数据不能是空的");
            }


        //拿准备请求的东西
        public User getmLginingUser() {
            return mLginingUser;
        }

        }



        //把响应返回的东西先准备在响应参数里
        public static final class ResponseValue implements UseCase.ResponseValue{

            private final User mCertifiedUser;//经过验证的用户

            public ResponseValue( User certifiedUser){

                mCertifiedUser = certifiedUser;
            }

            //来拿响应返回的东西

            public User getmCertifiedUser() {
                return mCertifiedUser;
            }
        }
}
