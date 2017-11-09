package com.sun.pd_mvp_clean.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sun.pd_mvp_clean.Injection;
import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.util.ActivityUtils;

/**
 * Created by Administrator on 2017/11/1.
 */

public class WelcomeActivity extends AppCompatActivity {

    private WelcomePresenter mWelcomePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //无标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //全屏模式
        setContentView(R.layout.welcome_act);
        WelcomeFragment mWelcomeFragment = (WelcomeFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(mWelcomeFragment == null){
            mWelcomeFragment = WelcomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mWelcomeFragment,R.id.contentFrame);
        }
        mWelcomePresenter = new WelcomePresenter(Injection.provideUseCaseHandler(),
                mWelcomeFragment,Injection.provideFindUser(getApplicationContext()),
                Injection.provideVerifyUser(getApplicationContext()));


    }
}
