package com.sun.pd_mvp_clean.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/30.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    private ArrayList<Fragment> myFragments;


    public void setMyFragments(ArrayList<Fragment> myFragments) {
        this.myFragments = myFragments;
    }

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
