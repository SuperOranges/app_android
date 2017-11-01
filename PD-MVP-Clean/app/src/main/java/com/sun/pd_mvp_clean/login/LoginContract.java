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

        }

        interface  Presenter extends BasePresenter{

                void verifyUserToRemote(@NonNull User user);
                void saveUserToLocal(@NonNull User user);
                void clerLocalUser();
        }
}
