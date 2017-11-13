package com.sun.pd_mvp_clean.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.util.NetUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/11/1.
 */

public class LoginFragment extends Fragment implements LoginContract.View {


    String usernameText;
    String passwordText;
    boolean buttonState1=false;
    boolean buttonState2=false;

    private TextView mErrorTextView;
    private LoginContract.Presenter mLoginPresenter;
    public  LoginFragment(){
        // Requires empty public constructor
    }
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mLoginPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.login_frag, container, false);
        mErrorTextView = (TextView)root.findViewById(R.id.errorHitOfLogin);

        Drawable success = getResources().getDrawable(R.drawable.success);
        success.setBounds(0, 0, success.getMinimumWidth(), success.getMinimumHeight());
        Drawable wrong =getResources().getDrawable(R.drawable.wrong);
        wrong.setBounds(0, 0, wrong.getMinimumWidth(), wrong.getMinimumHeight());

        EditText username = (EditText)root.findViewById(R.id.usernameOfLogin);
        if(username.getText().toString().trim().equals("")&&username.getText().toString().trim()==null){
            username.setCompoundDrawables(null,null,wrong,null);
            buttonState1 = false ;
        }else{
            usernameText = username.getText().toString().trim();
            username.setCompoundDrawables(null,null,success,null);
            buttonState1 = true ;
        }

        EditText password = (EditText)root.findViewById(R.id.passwordOfLogin);
        if(password.getText().toString().trim().equals("")&&password.getText().toString().trim()==null){
            username.setCompoundDrawables(null,null,wrong,null);
            buttonState2 = false ;
        }else{
            passwordText = password.getText().toString().trim();
            username.setCompoundDrawables(null,null,success,null);
            buttonState2 = true ;
        }

        Button loginButton = (Button)root.findViewById(R.id.login_in);
        //// TODO: 2017/11/10  



        return root;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean networkTest() {
        return NetUtils.isConnected(getActivity());
    }

    @Override
    public void showInterntError() {

    }

    @Override
    public void showInputFormatError() {

    }

    @Override
    public void showLoginFailed() {

    }

    @Override
    public void showOtherError() {

    }

    @Override
    public void intentToTasks(@NonNull User user, boolean state) {

    }
}
