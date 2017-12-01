package com.sun.pd_mvp_clean.tasks;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.sun.pd_mvp_clean.Injection;
import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.util.MyFragmentPagerAdapter;
import com.sun.pd_mvp_clean.util.ToastManager;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private static final String CURRENT_FILTERING_KEY1 = "CURRENT_FILTERING_KEY1";
    private static final String CURRENT_FILTERING_KEY2 = "CURRENT_FILTERING_KEY2";
    private DrawerLayout mDrawerLayout;

    private TasksPresenter mTasksPresenter1;
    private TasksPresenter mTasksPresenter2;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    //以下写法容易造成内存泄漏？？？ 在Activity没有挂掉之前一直持有了两个Fragment页面的对象
    //重写在Activity 声明周期里的 onstop()方法
    private TasksFragment  tasksFragment1;
    private TasksFragment  tasksFragment2;

    private String[] mTitleList = new String[]{"之前","今日"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        if (navView != null) {
            setupDrawerContent(navView);
        }

        //

        if (tasksFragment1 == null) {
            // Create the fragment
            tasksFragment1 = TasksFragment.newInstance(1);
//            ActivityUtils.addFragmentToActivity(
//                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }
        if(tasksFragment2 == null){
            tasksFragment2 = TasksFragment.newInstance(2);
        }
        //
        //Set up the Tabview and ViewPager
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mFragmentList.add(tasksFragment1);
        mFragmentList.add(tasksFragment2);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.setTitles(mTitleList);
        mAdapter.setMyFragments(mFragmentList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。


        // Create the presenter
        mTasksPresenter1 = new TasksPresenter(
                Injection.provideUseCaseHandler(),tasksFragment1,
                Injection.provideGetTasks(getApplicationContext()),1
        );
        mTasksPresenter2 = new TasksPresenter(
                Injection.provideUseCaseHandler(),tasksFragment2,
                Injection.provideGetTasks(getApplicationContext()),2
        );

        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            TaskFilterType currentFiltering0 =
                    (TaskFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY1);
            TaskFilterType currentFiltering1 =
                    (TaskFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY2);
            mTasksPresenter1.setFiltering(currentFiltering0);
            mTasksPresenter2.setFiltering(currentFiltering1);
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CURRENT_FILTERING_KEY1, mTasksPresenter1.getFiltering());
        outState.putSerializable(CURRENT_FILTERING_KEY2, mTasksPresenter2.getFiltering());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setCheckedItem(R.id.nav_today);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_today:
                                break;
                            case R.id.nav_history:
                                ToastManager.showShort(getApplicationContext(), "clicked history");
                                break;
                            case R.id.nav_profile:
                                ToastManager.showShort(getApplicationContext(), "clicked profile");
                                break;
                            default:
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
