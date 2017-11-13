package com.sun.pd_mvp_clean.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sun.pd_mvp_clean.Injection;
import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.util.ActivityUtils;

/**
 * Created by Administrator on 2017/11/1.
 */

public class LoginActivity extends AppCompatActivity {
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        LoginFragment loginFragment =(LoginFragment)getSupportFragmentManager().findFragmentById(R.id.loginContentFrame);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.loginContentFrame);
        }


        mLoginPresenter = new LoginPresenter(
                Injection.provideUseCaseHandler(),
                loginFragment,
                Injection.provideVerifyUser(getApplicationContext()),
                Injection.provideSaveUser(getApplicationContext()),
                Injection.provideClearAllUser(getApplicationContext())
        );
    }
}
