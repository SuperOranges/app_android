package com.sun.ckpt;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.kevin.tabindicator.TabPageIndicatorEx;
import com.sun.ckpt.util.TabFragment;
import com.sun.ckpt.util.TopBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private TopBar mTopbar;
    //
    private ViewPager mViewPager;
    private TabPageIndicatorEx mTabPageIndicatorEx;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private String[] mTitles = new String[] { "First Fragment!",
            "Second Fragment!", "Third Fragment!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
        mTopbar=(TopBar)findViewById(R.id.topBar);
        mTopbar.setButtonVisable(0, false);
        mTopbar.setButtonVisable(1, true);

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

        for (String title : mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
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
}
