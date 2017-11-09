package com.sun.pd_mvp_clean.welcome;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;
import com.sun.pd_mvp_clean.account.domain.model.User;


/**
 * Created by Administrator on 2017/11/1.
 */

public interface WelcomeContract {
    interface View extends BaseView<WelcomeContract.Presenter> {
        boolean  networkTest();//判断网络是否连接
        void intentToLogin(Boolean state, @Nullable int msg);//state 为true ， 跳转到login界面。
        //msg 参数为 0 表示网络未连接
        //msg 参数为 1 表示未发现本地用户信息
        //msg 参数为 2 表示用户名或密码不正确
        void intentToTasks(@NonNull User user ,boolean state);//state为true 跳转到task界面
    }

    interface  Presenter extends BasePresenter {

        void findUserFromLocal();
        void verifyUserToRemote(@NonNull User user);
    }
}
