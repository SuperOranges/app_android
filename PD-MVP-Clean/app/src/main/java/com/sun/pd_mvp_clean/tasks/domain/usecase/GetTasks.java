package com.sun.pd_mvp_clean.tasks.domain.usecase;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.data.source.TasksDataSource;
import com.sun.pd_mvp_clean.data.source.TasksRepository;
import com.sun.pd_mvp_clean.tasks.TaskFilterType;
import com.sun.pd_mvp_clean.tasks.domain.filter.FilterFactory;
import com.sun.pd_mvp_clean.tasks.domain.filter.TaskFilter;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;
import com.sun.pd_mvp_clean.util.DateUtils;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 孙毅 on 2017/11/25.
 */

public class GetTasks extends UseCase<GetTasks.RequestValues,GetTasks.ResponseValue> {

    private final TasksRepository mTasksRepository;

    private final FilterFactory mFilterFactory;

    public GetTasks(@NonNull TasksRepository tasksRepository, @NonNull FilterFactory filterFactory) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
        mFilterFactory = checkNotNull(filterFactory, "filterFactory cannot be null!");
    }
    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        if(requestValues.isForceUpdate()){
            mTasksRepository.refreshTasks(requestValues.getFlag());
        }
        switch (requestValues.getFlag()){
            case 1:
                mTasksRepository.getUncompletedTasks(1, requestValues.getToday(), new TasksDataSource.LoadTasksCallback() {
                    @Override
                    public void onTasksLoaded(List<Task> tasks) {
                        TaskFilterType currentFiltering = requestValues.getCurrentFiltering();
                        TaskFilter taskFilter = mFilterFactory.create(currentFiltering);

                        List<Task> tasksFiltered = taskFilter.filter(tasks);
                        ResponseValue responseValue = new ResponseValue(tasksFiltered);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getUseCaseCallback().onError();
                    }
                });
                break;
            case 2:
                mTasksRepository.getUncompletedTasks(2, requestValues.getToday(), new TasksDataSource.LoadTasksCallback() {
                    @Override
                    public void onTasksLoaded(List<Task> tasks) {
                        TaskFilterType currentFiltering = requestValues.getCurrentFiltering();
                        TaskFilter taskFilter = mFilterFactory.create(currentFiltering);

                        List<Task> tasksFiltered = taskFilter.filter(tasks);
                        ResponseValue responseValue = new ResponseValue(tasksFiltered);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getUseCaseCallback().onError();
                    }
                });
                break;
            case 3:
                mTasksRepository.getBeforeTasks(requestValues.getStartDay(),requestValues.getEndDay(),new TasksDataSource.LoadTasksCallback() {
                    @Override
                    public void onTasksLoaded(List<Task> tasks) {
                        TaskFilterType currentFiltering = requestValues.getCurrentFiltering();
                        TaskFilter taskFilter = mFilterFactory.create(currentFiltering);

                        List<Task> tasksFiltered = taskFilter.filter(tasks);
                        ResponseValue responseValue = new ResponseValue(tasksFiltered);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getUseCaseCallback().onError();
                    }
                });
                break;
            case 4:
                mTasksRepository.getFutureTasks(4,requestValues.getToday(),new TasksDataSource.LoadTasksCallback() {
                    @Override
                    public void onTasksLoaded(List<Task> tasks) {
                        TaskFilterType currentFiltering = requestValues.getCurrentFiltering();
                        TaskFilter taskFilter = mFilterFactory.create(currentFiltering);

                        List<Task> tasksFiltered = taskFilter.filter(tasks);
                        ResponseValue responseValue = new ResponseValue(tasksFiltered);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getUseCaseCallback().onError();
                    }
                });
                break;
            case 5:
                mTasksRepository.getFutureTasks(5,requestValues.getToday(),new TasksDataSource.LoadTasksCallback() {
                    @Override
                    public void onTasksLoaded(List<Task> tasks) {
                        TaskFilterType currentFiltering = requestValues.getCurrentFiltering();
                        TaskFilter taskFilter = mFilterFactory.create(currentFiltering);

                        List<Task> tasksFiltered = taskFilter.filter(tasks);
                        ResponseValue responseValue = new ResponseValue(tasksFiltered);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getUseCaseCallback().onError();
                    }
                });
                break;
            case 6:
                mTasksRepository.getFutureTasks(6,requestValues.getToday(),new TasksDataSource.LoadTasksCallback() {
                    @Override
                    public void onTasksLoaded(List<Task> tasks) {
                        TaskFilterType currentFiltering = requestValues.getCurrentFiltering();
                        TaskFilter taskFilter = mFilterFactory.create(currentFiltering);

                        List<Task> tasksFiltered = taskFilter.filter(tasks);
                        ResponseValue responseValue = new ResponseValue(tasksFiltered);
                        getUseCaseCallback().onSuccess(responseValue);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getUseCaseCallback().onError();
                    }
                });
                break;
            default:
                Log.e("GetTasks","flag input error");
                break;
        }

    }




    public static final class RequestValues implements UseCase.RequestValues {
        private final TaskFilterType mCurrentFiltering;//过滤类型
        private final boolean mForceUpdate;//是否需要远程请求数据
        private final int mFlag;//取得Task的模式 1-6，6种模式
        private final Date mToday;//今日日期
        private final Date mStartDay;//选择的开始日期
        private final Date mEndDay;//选择的结束日期

        public Date getToday() {
            return mToday;
        }

        public Date getStartDay() {
            return mStartDay;
        }

        public Date getEndDay() {
            return mEndDay;
        }

        public TaskFilterType getCurrentFiltering() {
            return mCurrentFiltering;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }

        public int getFlag() {
            return mFlag;
        }

        public RequestValues(@NonNull TaskFilterType currentFiltering, boolean forceUpdate, @NonNull int flag,
                             @Nullable Date today,@Nullable Date startDay,@Nullable Date endDay ) {
            mCurrentFiltering =checkNotNull(currentFiltering,"CurrentFiltering can not be null");
            mForceUpdate = forceUpdate;
            mFlag = checkNotNull(flag,"flag can not be null");
            mToday = today;
            mStartDay = startDay;
            mEndDay = endDay;
        }

    }
    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<Task> mTasks;

        public ResponseValue(@NonNull List<Task> tasks) {
            mTasks = checkNotNull(tasks, "tasks cannot be null!");
        }

        public List<Task> getTasks() {
            return mTasks;
        }
    }
}
