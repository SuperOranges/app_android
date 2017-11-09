package com.sun.pd_mvp_clean.login;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;
import com.sun.pd_mvp_clean.account.domain.model.User;

/**
 * Created by Administrator on 2017/10/23.
 */

public interface LoginContract {
        interface View extends BaseView<Presenter>{
                void showInterntError();//提示网络异常
                void showInputFormatError();//提示输入格式异常
                void showLoginFailed();//提示登录失败，可能是用户名或密码错误等
                void showOtherError();//提示其他异常出现
                void intentToTasks(@NonNull User user ,boolean state);//state为true 跳转到task界面
        }

        interface  Presenter extends BasePresenter{

                void verifyUserToRemote(@NonNull User user);
                void saveUserToLocal(@NonNull User user);
                void clearLocalUser();
        }
}
