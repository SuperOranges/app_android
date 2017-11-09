package com.sun.pd_mvp_clean.login;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/11/1.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mLoginPresenter;
    public  LoginFragment(){
        // Requires empty public constructor
    }
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    
    //// TODO: 2017/11/9  


}
