package com.sun.ckpt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.kevin.tabindicator.TabPageIndicatorEx;
import com.sun.ckpt.util.TabFragment;
import com.sun.ckpt.util.ToastManager;
import com.sun.ckpt.util.TopBar;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private TopBar mTopbar;
    //
    //二维码扫描
    private TextView resultTextView;
    //
    private ViewPager mViewPager;
    private TabPageIndicatorEx mTabPageIndicatorEx;

    private List<Fragment> mTabs = new ArrayList<>();

    private FragmentPagerAdapter mAdapter;

    private List<String> mData0 = new ArrayList<String>();
    private List<String> mData1 = new ArrayList<String>();
    private List<String> mData2 = new ArrayList<String>();

    private ArrayList<List<String>> mTitles = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
        mTopbar=(TopBar)findViewById(R.id.topBar);
        mTopbar.setButtonVisable(0, false);
        mTopbar.setButtonVisable(1, true);
        // 为topbar注册监听事件，传入定义的接口
        // 并以匿名类的方式实现接口内的方法
        mTopbar.setOnTopbarClickListener(
                new TopBar.topbarClickListener() {
                    @Override
                    public void rightClick() {
                        //进入二维码扫描模块
                        Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
                        startActivityForResult(openCameraIntent, 0);
                    }
                    @Override
                    public void leftClick() {

                    }
                });
        //二维码
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
    }
    /**
     * 初始化View
     */
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabPageIndicatorEx = (TabPageIndicatorEx) findViewById(R.id.sssssss);

        initTabIndicator();
        initViewPager();
    }
    private void initViewPager() {
        // 增加测试数据
        mData0.add("Recycler1");
        mData0.add("Recycler2");
        mData0.add("Recycler3");
        mData0.add("Recycler1");
        mData0.add("Recycler2");
        mData0.add("Recycler3");
        mData0.add("Recycler1");
        mData0.add("Recycler2");
        mData0.add("Recycler3");
        mData0.add("Recycler1");
        mData0.add("Recycler2");
        mData0.add("Recycler3");

        mData1.add("Recycler4");
        mData1.add("Recycler5");
        mData1.add("Recycler6");
        mData2.add("Recycler7");
        mData2.add("Recycler8");
        mData2.add("Recycler9");
        mTitles.add(mData0);
        mTitles.add(mData1);
        mTitles.add(mData2);
        for (List<String> title : mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle args = new Bundle();
            args.putStringArrayList("title", (ArrayList<String>) title);
            tabFragment.setArguments(args);
            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
    }
    /**
     * 初始化事件
     */
    private void initEvents() {

        mTabPageIndicatorEx.setOnTabSelectedListener(new TabPageIndicatorEx.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int index) {
                mViewPager.setCurrentItem(index, false);
            }
        });
    }
//
    private void initTabIndicator() {
        mTabPageIndicatorEx.setViewPager(mViewPager);
        //设置指示点
        // mTabPageIndicatorEx.setIndicateDisplay(2, true);
    }
    //二维码
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Bundle bundle = data.getExtras();

            String scanResult = bundle.getString("result");

            resultTextView.setText(scanResult);

        }

    }

}
