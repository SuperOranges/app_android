package com.sun.pd_mvp_clean.login;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;

/**
 * Created by Administrator on 2017/10/23.
 */

public interface LoginContract {
        interface View extends BaseView<Presenter>{

        }

        interface  Presenter extends BasePresenter{

        }
}
