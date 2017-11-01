package com.sun.pd_mvp_clean.welcome;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.login.LoginContract;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface WelcomeContract {
    interface View extends BaseView<LoginContract.Presenter> {

    }

    interface  Presenter extends BasePresenter {

        void findUserFromLocal();
        void verifyUserToRemote(@NonNull User user);
    }
}
