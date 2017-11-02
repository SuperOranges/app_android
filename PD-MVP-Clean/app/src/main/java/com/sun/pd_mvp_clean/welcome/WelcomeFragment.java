package com.sun.pd_mvp_clean.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sun.pd_mvp_clean.account.domain.model.User;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/11/1.
 */

public class WelcomeFragment extends Fragment implements WelcomeContract.View {
    private WelcomeContract.Presenter mWelcomePresenter;

    public WelcomeFragment() {
            // Requires empty public constructor
        }

    public static WelcomeFragment newInstance() {
            return new WelcomeFragment();
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mWelcomePresenter.start();
    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        mWelcomePresenter = checkNotNull(presenter);
    }

    @Override
    public void intentToLogin(Boolean state) {

    }

    @Override
    public void intentToTasks(@NonNull User user, boolean state) {

    }
}
