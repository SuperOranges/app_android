package com.sun.pd_mvp_clean.welcome;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;
import com.sun.pd_mvp_clean.account.domain.model.User;


/**
 * Created by Administrator on 2017/11/1.
 */

public interface WelcomeContract {
    interface View extends BaseView<WelcomeContract.Presenter> {
        void intentToLogin(Boolean state);//state 为true ， 跳转到login界面。
        void intentToTasks(@NonNull User user ,boolean state);//state为true 跳转到task界面
    }

    interface  Presenter extends BasePresenter {

        void findUserFromLocal();
        void verifyUserToRemote(@NonNull User user);
    }
}
