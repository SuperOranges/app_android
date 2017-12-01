package com.sun.pd_mvp_clean.tasks;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.UseCaseHandler;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import com.sun.pd_mvp_clean.tasks.domain.usecase.GetTasks;
import com.sun.pd_mvp_clean.util.DateUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 孙毅 on 2017/11/14.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private final TasksContract.View mTasksView;
    private final GetTasks mGetTasks;

    private TaskFilterType mCurrentFiltering = TaskFilterType.ALL_TASKS;

    private boolean mFirstLoad = true;

    private final UseCaseHandler mUseCaseHandler;
    private final int mFlag;

    public TasksPresenter(@NonNull UseCaseHandler useCaseHandler,
                          @NonNull TasksContract.View tasksView, @NonNull GetTasks getTasks,
                        @NonNull int flag) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "usecaseHandler cannot be null");
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");
        mGetTasks = checkNotNull(getTasks, "getTask cannot be null!");
        mFlag = checkNotNull(flag, "flag cannot be null!");
        mTasksView.setPresenter(this);
    }
    @Override
    public void start() {
        loadTasks(mFlag,false);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(int flag,boolean forceUpdate) {
        loadTasks(flag,forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    /**
     *
     * @param flag  此参数是模式参数
     * @param forceUpdate 此参数为true是刷新数据源中的数据
     * @param showLoadingUI 此参数为true是刷新载入UI
     */
    private void loadTasks(int flag ,boolean forceUpdate,final boolean showLoadingUI){
        if (showLoadingUI) {
            mTasksView.setLoadingIndicator(true);
        }
        GetTasks.RequestValues requestValues2 = new GetTasks.RequestValues(mCurrentFiltering,forceUpdate,flag,
                DateUtils.getSystemDatetime(),null,null);

        mUseCaseHandler.execute(mGetTasks, requestValues2, new UseCase.UseCaseCallback<GetTasks.ResponseValue>() {
            @Override
            public void onSuccess(GetTasks.ResponseValue response) {
                List<Task> tasks = response.getTasks();

                if (showLoadingUI) {
                    mTasksView.setLoadingIndicator(false);
                }
                processTasks(tasks);

            }

            @Override
            public void onError() {

                mTasksView.showLoadingTasksError();
            }
        });
    }


    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyTasks();
        } else {
            // Show the list of tasks
            mTasksView.showTasks(tasks);
            // Set the filter label's text.
            showFilterLabel();
        }
    }
    private void processEmptyTasks() {
        switch (mCurrentFiltering) {
            case NOT_BEGIN:
                mTasksView.showNoNotBeginTasks();
                break;
            case IN_PROCESS:
                mTasksView.showNoInProcessTasks();
                break;
            case COMPLETED:
                mTasksView.showNoCompletedTasks();
            default:
                mTasksView.showNoTasks();
                break;
        }
    }

    private void showFilterLabel() {
        switch (mCurrentFiltering) {
            case NOT_BEGIN:
                mTasksView.showNotBeginLable();
                break;
            case IN_PROCESS:
                mTasksView.showInProcessLable();
                break;
            case COMPLETED:
                mTasksView.showCompletedLable();
            default:
                mTasksView.showAllTasksLable();
                break;
        }
    }


    @Override
    public void setFiltering(TaskFilterType requestType) {
        mCurrentFiltering = requestType;
    }

    @Override
    public TaskFilterType getFiltering() {
        return mCurrentFiltering;
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {
        checkNotNull(requestedTask, "requestedTask cannot be null!");
        mTasksView.showTaskDetailsUi(requestedTask.getTaskID());
    }
}
