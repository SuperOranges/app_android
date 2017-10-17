package com.sun.ckpt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.sun.ckpt.util.HorizontalProgressBarWithNumber;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 孙毅 on 2017/10/5.
 * 点开app的欢迎页面，大概会持续三秒钟
 */

public class WelcomeActivity extends Activity {
    private HorizontalProgressBarWithNumber  mProgressBar;
    private static final int MSG_PROGRESS_UPDATE = 0x110;

    private Handler mHandler = new Handler() {
        //创建一个负责更新进度条的Handler
        public void handleMessage(android.os.Message msg) {
            int progress = mProgressBar.getProgress();
            mProgressBar.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 30);//延时3ms再次发送消息
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //无标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //全屏模式
        setContentView(R.layout.welcome);
        //进度条
        mProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.id_progressbar01);
        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);

        //以下获取组件
        RelativeLayout wel=(RelativeLayout)findViewById(R.id.welcome);
        //背景透明度在3秒内由0.4 改变到 1.0
        AlphaAnimation alp = new AlphaAnimation(0.4f,1.0f);
        alp.setDuration(3000);//设置时间为3000ms
        wel.startAnimation(alp);

        //设置定时器
        Timer timer = new Timer();
        //设置定时器执行的任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        //通过Timer的schedule()方法设置时间为3001ms,计时结束后执行TimerTask
        timer.schedule(timerTask,3001);
    }
}
