package com.sun.ckpt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sun.ckpt.util.NetUtils;
import com.sun.ckpt.util.ToastManager;
import com.sun.ckpt.util.TopBar;

public class LoginActivity extends Activity {
    private TopBar mTopbar;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        mTopbar=(TopBar)findViewById(R.id.topBar);
        // 为topbar注册监听事件，传入定义的接口
        // 并以匿名类的方式实现接口内的方法
//        mTopbar.setOnTopbarClickListener(
//                new TopBar.topbarClickListener() {
//                    @Override
//                    public void rightClick() {
//                        ToastManager.showShort(LoginActivity.this,"right");
//                    }
//                    @Override
//                    public void leftClick() {
//                        ToastManager.showShort(LoginActivity.this,"left");
//                    }
//                });

        // 控制topbar上组件的状态

        mTopbar.setButtonVisable(0, false);
        mTopbar.setButtonVisable(1, false);

    }
    public void onLoginClick(View v){
        if(NetUtils.isConnected(LoginActivity.this)==true){
            if(userName.getText().toString().isEmpty()&&password.getText().toString().isEmpty()){
                ToastManager.showShort(LoginActivity.this,"用户名或密码不能为空");
            }else{
                if(userName.getText().toString().equals("aaa")&password.getText().toString().equals("a123")){
                    ToastManager.showShort(LoginActivity.this,"欢迎您，"+userName.getText().toString());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    ToastManager.showShort(LoginActivity.this,"用户名或密码不正确");
                }
            }
        }else{
            ToastManager.showShort(LoginActivity.this,"网络未连接");
        }
    }
}
