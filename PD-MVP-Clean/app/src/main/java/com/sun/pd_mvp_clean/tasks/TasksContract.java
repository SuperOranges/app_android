package com.sun.pd_mvp_clean.tasks;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.List;

/**
 * Created by 孙毅 on 2017/11/14.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        boolean isActive();
        void showLoadingTasksError();

        void showTasks(List<Task> tasks);
        void showTaskDetailsUi(String taskId);

        void showNoTasks();
        void showNoInProcessTasks();
        void showNoNotBeginTasks();

        void showAllTasksLable();
        void showNotBeginLable();
        void showInProcessLable();
        void showFilteringPopUpMenu();
    }

    interface  Presenter extends BasePresenter {
        void result(int requestCode ,int resultCode);
        void loadTasks(int flag,boolean forceUpdate);
        void setFiltering(TaskFilterType requestType);
        TaskFilterType getFiltering();
        void openTaskDetails(@NonNull Task requestedTask);
    }
}
