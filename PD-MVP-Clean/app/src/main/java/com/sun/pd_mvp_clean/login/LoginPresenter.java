package com.sun.pd_mvp_clean.login;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.UseCaseHandler;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.account.domain.usecase.ClearAllUser;
import com.sun.pd_mvp_clean.account.domain.usecase.SaveUser;
import com.sun.pd_mvp_clean.account.domain.usecase.VerifyUser;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/11/1.
 */

public class LoginPresenter  implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private final VerifyUser mVerifyUser;
    private final SaveUser mSaveUser;
    private final ClearAllUser mClearAllUser;
    private final UseCaseHandler mUseCaseHandler;

    @NonNull
    private User mUser;

    public LoginPresenter(@NonNull UseCaseHandler useCaseHandler, @NonNull LoginContract.View welcomeView,
                             @NonNull VerifyUser verifyUser,@NonNull User user,@NonNull SaveUser saveUser,
                          @NonNull ClearAllUser clearAllUser){
        mLoginView = checkNotNull(welcomeView,"welcomeView can not be null");
        mVerifyUser = checkNotNull(verifyUser,"verifyUser can not be null");
        mUseCaseHandler = checkNotNull(useCaseHandler,"useCaseHandler can not be null");
        mUser = checkNotNull(user);
        mSaveUser = checkNotNull(saveUser,"saveUser can not be null");
        mClearAllUser = checkNotNull(clearAllUser,"clearAllUser can not be null");
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

        verifyUserToRemote(mUser);
    }

    @Override
    public void verifyUserToRemote(@NonNull User user) {
        mUseCaseHandler.execute(mVerifyUser, new VerifyUser.RequestValues(user), new UseCase.UseCaseCallback<VerifyUser.ResponseValue>() {
            @Override
            public void onSuccess(VerifyUser.ResponseValue response) {
                clearLocalUser();
                saveUserToLocal(response.getUser());


            }

            @Override
            public void onError() {
                mLoginView.showLoginFailed();
            }
        });
    }

    @Override
    public void saveUserToLocal(@NonNull User user) {
            checkNotNull(user,"user of saveUserToLocal can not be null");
        mUseCaseHandler.execute(mSaveUser, new SaveUser.RequestValues(user), new UseCase.UseCaseCallback<SaveUser.ResponseValue>() {
            @Override
            public void onSuccess(SaveUser.ResponseValue response) {
                mLoginView.intentToTasks(response.getUser(),true);
            }

            @Override
            public void onError() {
                mLoginView.showOtherError();
            }
        });
    }

    @Override
    public void clearLocalUser() {
        mUseCaseHandler.execute(mClearAllUser, new ClearAllUser.RequestValues(), new UseCase.UseCaseCallback<ClearAllUser.ResponseValue>() {
            @Override
            public void onSuccess(ClearAllUser.ResponseValue response) {

            }

            @Override
            public void onError() {
                mLoginView.showOtherError();
            }
        });
    }
}
