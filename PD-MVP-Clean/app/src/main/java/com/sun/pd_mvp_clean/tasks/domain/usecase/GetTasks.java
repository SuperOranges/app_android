package com.sun.pd_mvp_clean.tasks.domain.usecase;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.data.source.TasksDataSource;
import com.sun.pd_mvp_clean.data.source.TasksRepository;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

public class GetTasks extends UseCase<GetTasks.RequestValues,GetTasks.ResponseValue> {

             private final TasksRepository mTasksRepository;
             public  GetTasks(@NonNull TasksRepository tasksRepository){
                 mTasksRepository = checkNotNull(tasksRepository,"tasksRepository can not be null");
             }

            @Override
            protected void executeUseCase(final RequestValues requestValues) {
                    if(requestValues.isForceUpdate()){
                        mTasksRepository.refreshTasks();
                    }
                    mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
                        @Override
                        public void onTasksLoaded( List<Task> tasks) {

                            ResponseValue responseValue = new ResponseValue(tasks);
                            getUseCaseCallback().onSuccess(responseValue);
                        }

                        @Override
                        public void onDataNotAvailable() {
                            getUseCaseCallback().onError();
                        }
                    });
            }

            public static final class RequestValues implements UseCase.RequestValues{
                    private final boolean isForceUpdate;
                    public RequestValues(boolean forceUpdate){
                            isForceUpdate = forceUpdate;
                }
                    public boolean isForceUpdate(){
                            return isForceUpdate;
                    }
            }

            public static final class ResponseValue implements UseCase.ResponseValue{
                    private final List<Task> mTasks;
                    public ResponseValue(@NonNull List<Task> tasks){
                        mTasks = checkNotNull(tasks,"tasks can not be null!");
                    }
                    public List<Task> getTasks(){
                        return mTasks;
                    }
            }
}
