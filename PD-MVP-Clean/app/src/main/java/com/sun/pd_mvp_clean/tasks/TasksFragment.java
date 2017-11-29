package com.sun.pd_mvp_clean.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sun.pd_mvp_clean.R;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 孙毅 on 2017/11/14.
 */

public class TasksFragment extends Fragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;

    private TasksAdapter mListAdapter;

    private View mNoTasksView;

    private LinearLayout mTasksView;

    private TextView mToday_tv;
    private TextView mFilteringLabelView;

    public TasksFragment() {
        // Requires empty public constructor
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mItemListener);
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
        mNoTasksView = root.findViewById(R.id.noTasks);
        //// TODO: 2017/11/29  

        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showTasks(List<Task> tasks) {

    }

    @Override
    public void showTaskDetailsUi(String taskId) {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void showNoInProcessTasks() {

    }

    @Override
    public void showNoNotBeginTasks() {

    }

    @Override
    public void showAllTasksLable() {

    }

    @Override
    public void showNotBeginLable() {

    }

    @Override
    public void showInProcessLable() {

    }

    @Override
    public void showFilteringPopUpMenu() {

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

            taskID_tv.setText("日计划号："+task.getTaskID());
            taskStatus.setText("当前状态："+task.taskStatus());

            //扫码按钮未实现

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onTaskClick(task);
                }
            });

            return rowView;
        }



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
