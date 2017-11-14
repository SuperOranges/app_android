package com.sun.pd_mvp_clean.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.tasks.TasksActivity;
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

        Bundle bundle=getActivity().getIntent().getExtras();
        int msg = bundle.getInt("ErrorInfo");
        switch (msg){
            case 0:
                showInterntError();
                break;
            case 1:
                break;
            case 2:
                showLoginFailed();
                break;
            default:
                break;
        }

        
        //// TODO: 2017/11/14 登录的输入框以及按钮变色有逻辑问题需要修正 ！！！！！
        
        
        
        final Drawable success = getResources().getDrawable(R.drawable.success);
        success.setBounds(0, 0, success.getMinimumWidth(), success.getMinimumHeight());
        final Drawable wrong =getResources().getDrawable(R.drawable.wrong);
        wrong.setBounds(0, 0, wrong.getMinimumWidth(), wrong.getMinimumHeight());

        final Button mLoginButton = (Button)root.findViewById(R.id.login_in);
        mLoginButton.setEnabled(false);
        mLoginButton.setBackgroundColor(0xffC4C4C4);



        final EditText username = (EditText)root.findViewById(R.id.usernameOfLogin);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLoginButton.setEnabled(false);
                mLoginButton.setBackgroundColor(0xffC4C4C4);
                buttonState2 = false ;
                username.setCompoundDrawables(null,null,wrong,null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLoginButton.setEnabled(false);
                mLoginButton.setBackgroundColor(0xffC4C4C4);
                buttonState1 = false ;
                username.setCompoundDrawables(null,null,wrong,null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().equals("")&&editable.toString().trim()==null){
                    username.setCompoundDrawables(null,null,wrong,null);
                    buttonState1 = false ;
                }else{

                    username.setCompoundDrawables(null,null,success,null);
                    buttonState1 = true ;
                    if(buttonState2 == true){
                        mLoginButton.setBackgroundColor(0xffC0FF3E);
                        mLoginButton.setEnabled(true);
                    }
                }
            }
        });


        final EditText password = (EditText)root.findViewById(R.id.passwordOfLogin);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLoginButton.setEnabled(false);
                mLoginButton.setBackgroundColor(0xffC4C4C4);
                buttonState2 = false ;
                password.setCompoundDrawables(null,null,wrong,null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLoginButton.setEnabled(false);
                mLoginButton.setBackgroundColor(0xffC4C4C4);
                buttonState2 = false ;
                password.setCompoundDrawables(null,null,wrong,null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().equals("") && editable.toString().trim()==null){
                    password.setCompoundDrawables(null,null,wrong,null);
                    buttonState2 = false ;
                }else{

                    password.setCompoundDrawables(null,null,success,null);
                    buttonState2 = true ;
                    if(buttonState1 == true){
                        mLoginButton.setBackgroundColor(0xffC0FF3E);
                        mLoginButton.setEnabled(true);
                    }
                }

            }

        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(networkTest()==true){
                    usernameText = username.getText().toString().trim();
                    passwordText = password.getText().toString().trim();

                    mLoginPresenter.clearLocalUser();
                    mLoginPresenter.verifyUserToRemote(new User(usernameText,passwordText));
                }else{
                    showInterntError();
                }
            }
        });

        return root;
    }

//    public void onLoginClick(LoginContract.View v){
//        if(networkTest()==true){
//            mLoginPresenter.clearLocalUser();
//            mLoginPresenter.verifyUserToRemote(new User(usernameText,passwordText));
//        }else{
//            showInterntError();
//        }
//    }
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
        showWarningLogo();
        mErrorTextView.setText("网络连接异常");
    }

    @Override
    public void showInputFormatError() {
        showWarningLogo();
        mErrorTextView.setText("输入字符格式异常");
    }

    @Override
    public void showLoginFailed() {
        showWarningLogo();
        mErrorTextView.setText("用户名或密码错误");
    }

    @Override
    public void showOtherError() {
        showWarningLogo();
        mErrorTextView.setText("发生其他异常");
    }

    @Override
    public void intentToTasks(@NonNull User user, boolean state) {
        Intent intent = new Intent(getActivity(), TasksActivity.class);
        intent.putExtra("userInfo",user);
        startActivity(intent);
        //把用户信息传到了TaskActivity中，通过getSerializableExtra()方法来获取通过参数传递过来的序列化对象
    }

    private void showWarningLogo(){
        final Drawable warning = getResources().getDrawable(R.drawable.warning);
        warning.setBounds(0, 0, warning.getMinimumWidth(), warning.getMinimumHeight());
        mErrorTextView.setCompoundDrawables(warning,null,null,null);
    }

}
