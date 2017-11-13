package com.sun.pd_mvp_clean.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.tasks.TasksActivity;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.login.LoginActivity;
import com.sun.pd_mvp_clean.util.NetUtils;


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
        mWelcomePresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        mWelcomePresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.welcome_frag, container, false);
        return root;
    }

    @Override
    public boolean  networkTest() {

        return NetUtils.isConnected(getActivity());
    }

    @Override
    public void intentToLogin(Boolean state,@Nullable int msg) {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        intent.putExtra("ErrorInfo",msg);
        startActivity(intent);

    }
    /**如果没有跳转就是fragment和activity没有进行绑定
     *
     */
    @Override
    public void intentToTasks(@NonNull User user, boolean state) {
        Intent intent = new Intent(getActivity(), TasksActivity.class);
        intent.putExtra("userInfo",user);
        startActivity(intent);
        //把用户信息传到了TaskActivity中，通过getSerializableExtra()方法来获取通过参数传递过来的序列化对象
    }
}
