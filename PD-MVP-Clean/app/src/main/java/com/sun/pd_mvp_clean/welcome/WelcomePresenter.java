package com.sun.pd_mvp_clean.welcome;

import android.support.annotation.NonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.UseCaseHandler;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.account.domain.usecase.FindUser;
import com.sun.pd_mvp_clean.account.domain.usecase.VerifyUser;

/**
 * Created by Administrator on 2017/11/1.
 */

public class WelcomePresenter implements WelcomeContract.Presenter {
    private final WelcomeContract.View mWelcomeView;
    private final FindUser mFindUser;
    private final VerifyUser mVerifyUser;

    private final UseCaseHandler mUseCaseHandler;

    public WelcomePresenter(@NonNull UseCaseHandler useCaseHandler,@NonNull WelcomeContract.View welcomeView,
                            @NonNull FindUser findUser,@NonNull VerifyUser verifyUser){
        mWelcomeView = checkNotNull(welcomeView,"welcomeView can not be null");
        mFindUser = checkNotNull(findUser,"FindView can not be null");
        mVerifyUser = checkNotNull(verifyUser,"verifyUser can not be null");
        mUseCaseHandler = checkNotNull(useCaseHandler,"useCaseHandler can not be null");

        mWelcomeView.setPresenter(this);
    }

    @Override
    public void start() {
            findUserFromLocal();
    }



    @Override
    public void findUserFromLocal() {
        mUseCaseHandler.execute(mFindUser, new FindUser.RequestValues(), new UseCase.UseCaseCallback<FindUser.ResponseValue>() {
            @Override
            public void onSuccess(FindUser.ResponseValue response) {
                    verifyUserToRemote(response.getUser());
            }

            @Override
            public void onError() {
                    mWelcomeView.intentToLogin(true);
            }
        });
    }

    @Override
    public void verifyUserToRemote(@NonNull User user) {

        mUseCaseHandler.execute(mVerifyUser, new VerifyUser.RequestValues(user), new UseCase.UseCaseCallback<VerifyUser.ResponseValue>() {
            @Override
            public void onSuccess(VerifyUser.ResponseValue response) {
                mWelcomeView.intentToTasks(response.getUser(),true);
            }

            @Override
            public void onError() {
                mWelcomeView.intentToLogin(true);
            }
        });
    }
}