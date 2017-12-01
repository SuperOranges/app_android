package com.sun.pd_mvp_clean.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Matrix2f;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;
import com.sun.pd_mvp_clean.util.ToastManager;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 孙毅 on 2017/11/14.
 *
 *
 * 此fragment显示的之前未完成，今日未完成，今日全部明日全部和后日全部日计划的模板；
 */
//// TODO: 2017/11/30 完善此模板以适应今日全部明日全部和后日全部的日计划的展示 
public class TasksFragment extends Fragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;

    private TasksAdapter mListAdapter;

    private View mNoTasksView;

    private LinearLayout mTasksView;

    private TextView mNotaskHintView;
    private TextView mFilteringLabelView;
    private  int mFlag;

    public TasksFragment(){
        // Requires empty public constructor
    }

    public static TasksFragment newInstance(int flag ) {
        TasksFragment f = new TasksFragment();
        Bundle args = new Bundle();
        args.putInt("flag", flag);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mItemListener);
        mFlag = getArguments().getInt("flag");
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.task_frag, container, false);
        //tasks view
        ListView listView = (ListView) root.findViewById(R.id.taskfrg_lv_today);
        listView.setAdapter(mListAdapter);
        mFilteringLabelView = (TextView) root.findViewById(R.id.taskfrg_tv_filteringLabel);
        mTasksView = (LinearLayout) root.findViewById(R.id.tasks_LV);

        //notasks view
        mNoTasksView = (LinearLayout)root.findViewById(R.id.noTasks);
        mNotaskHintView =(TextView) root.findViewById(R.id.noTasksMain);
        //// TODO: 2017/11/29  
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTasks(mFlag,false);
            }
        });

        setHasOptionsMenu(true);
        return root;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                showFilteringPopUpMenu();
                break;
            case R.id.menu_refresh:
                mPresenter.loadTasks(mFlag,true);
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(mFlag <= 4){
            inflater.inflate(R.menu.tasks_fragment_menu1, menu);
        }else{
            inflater.inflate(R.menu.tasks_fragment_menu2, menu);
        }

    }
    @Override
    public void showFilteringPopUpMenu() {
        if(mFlag <= 2) {
            PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.menu_filter));
            popup.getMenuInflater().inflate(R.menu.filter_tasks1, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.notBegin1:
                            mPresenter.setFiltering(TaskFilterType.NOT_BEGIN);
                            break;
                        case R.id.inProcess1:
                            mPresenter.setFiltering(TaskFilterType.IN_PROCESS);
                            break;
                        default:
                            mPresenter.setFiltering(TaskFilterType.ALL_TASKS);
                            break;
                    }
                    mPresenter.loadTasks(mFlag, false);
                    return true;
                }
            });
            popup.show();
        }
        if(mFlag==4){
            PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.menu_filter));
            popup.getMenuInflater().inflate(R.menu.filter_tasks2, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.notBegin2:
                            mPresenter.setFiltering(TaskFilterType.NOT_BEGIN);
                            break;
                        case R.id.inProcess2:
                            mPresenter.setFiltering(TaskFilterType.IN_PROCESS);
                            break;
                        case R.id.completed2:
                            mPresenter.setFiltering(TaskFilterType.COMPLETED);
                            break;
                        default:
                            mPresenter.setFiltering(TaskFilterType.ALL_TASKS);
                            break;
                    }
                    mPresenter.loadTasks(mFlag, false);
                    return true;
                }
            });
            popup.show();
        }
    }

    @Override
    public void setLoadingIndicator(final  boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    //这个方法可能不需要
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showLoadingTasksError() {
        ToastManager.showShort(getContext(),"载入计划失败！");
    }

    @Override
    public void showTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);

        mTasksView.setVisibility(View.VISIBLE);
        mNoTasksView.setVisibility(View.GONE);
    }

    //暂时不实现
    @Override
    public void showTaskDetailsUi(String taskId) {
        ToastManager.showShort(getContext(),taskId);
    }

    @Override
    public void showNoTasks() {
        showNoTasksViews("没有更多的日计划啦！");
    }

    @Override
    public void showNoInProcessTasks() {
        showNoTasksViews("没有正在处理中的日计划");
    }

    @Override
    public void showNoNotBeginTasks() {
        showNoTasksViews("没有未开工的日计划");
    }
    @Override
    public void showNoCompletedTasks() {
        showNoTasksViews("没有已完成的日计划");
    }

    private void showNoTasksViews(String mainText) {
        mTasksView.setVisibility(View.GONE);
        mNoTasksView.setVisibility(View.VISIBLE);
        mNotaskHintView.setText(mainText);
    }
    @Override
    public void showAllTasksLable() {
        mFilteringLabelView.setText(tipsText()+"未完成计划");
    }

    @Override
    public void showNotBeginLable() {
        mFilteringLabelView.setText(tipsText()+"未开工计划");
    }

    @Override
    public void showInProcessLable() {
        mFilteringLabelView.setText(tipsText()+"处理中计划");
    }

    @Override
    public void showCompletedLable() {
            mFilteringLabelView.setText(tipsText()+"已完成计划");
    }

    private static class TasksAdapter extends BaseAdapter{
        private List<Task> mTasks;
        private TaskItemListener mItemListener;
        public TasksAdapter(List<Task> tasks, TaskItemListener itemListener) {
            setList(tasks);
            mItemListener = itemListener;
        }

        public void replaceData(List<Task> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }


        private void setList(List<Task> tasks) {
            mTasks = checkNotNull(tasks);
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Task getItem(int i) {
            return mTasks.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.task_item, viewGroup, false);
            }

            final Task task = getItem(i);

            TextView taskID_tv = (TextView) rowView.findViewById(R.id.item_taskID);
            TextView taskStatus = (TextView)rowView.findViewById(R.id.item_status);
            CardView taskCardView = (CardView)rowView.findViewById(R.id.item_cd);
            ImageButton myImageButton = (ImageButton)rowView.findViewById(R.id.item_scan);
            taskCardView.setBackgroundColor(colorCardBGJudge(task));
            taskID_tv.setText("日计划号："+task.getTaskID());
            taskStatus.setText("当前状态："+task.taskStatus());


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onTaskClick(task);
                }
            });
            //扫码按钮未实现

            myImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastManager.showShort(view.getContext(),task.getTaskID()+"button");
                }
            });
            return rowView;
        }



    }
    private  String tipsText(){
        switch (mFlag){
            case 1:
                return "之前";
            case 2:
                return "今日";
            case 4:
                return "今日";
            case 5:
                return "明日";
            case 6:
                return "后日";
            default:
                return "";
        }
    }

    //判断cardview的背景色
    private static int colorCardBGJudge(Task task){
       if(task.isCompleted()){
           if(task.isNormalCompleted()){
               return 0xff638F48;
           }
           if(task.isOvertimeCompleted()){
               return 0xffF78132;
           }
       }else{
           if(task.isBegin()){
               if(task.isOvertimeThreeDaysUnComplete()){
                   return 0xffBC7D49;
               }
               if(task.isOverTime()){
                   return 0xffFFBE43;
               }
               return 0xff317F78;
           }else{
               return 0xff61C8CB;
           }
       }
       return 0xffffffff;
    }

    TaskItemListener mItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            mPresenter.openTaskDetails(clickedTask);
        }
    };
    public interface TaskItemListener {
        void onTaskClick(Task clickedTask);
    }
}
