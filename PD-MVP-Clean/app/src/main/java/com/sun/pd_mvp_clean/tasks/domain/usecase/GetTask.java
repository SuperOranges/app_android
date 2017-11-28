package com.sun.pd_mvp_clean.tasks.domain.usecase;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.data.source.TasksDataSource;
import com.sun.pd_mvp_clean.data.source.TasksRepository;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 孙毅 on 2017/11/25.
 */

public class GetTask extends UseCase<GetTask.RequestValues,GetTask.ResponseValue> {

    private final TasksRepository mTasksRepository;

    public GetTask(@NonNull TasksRepository tasksRepository) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mTasksRepository.getTaskById(requestValues.getFlag(), requestValues.getTaskID(), new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                if(task!=null){
                    ResponseValue responseValue = new ResponseValue(task);
                    getUseCaseCallback().onSuccess(responseValue);
                }else{
                    getUseCaseCallback().onError();
                }
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }




    public static final class RequestValues implements UseCase.RequestValues {
        private  final String mTaskID;
        private  final int mFlag;

        public String getTaskID() {
            return mTaskID;
        }

        public int getFlag() {
            return mFlag;
        }

        public RequestValues(@NonNull String taskID, @NonNull int flag) {
            mTaskID = checkNotNull(taskID,"taskID can not be null");
            mFlag = checkNotNull(flag,"flag can not be null");
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue {
        private Task mTask;

        public ResponseValue(@NonNull Task task) {
            mTask = checkNotNull(task, "task cannot be null!");
        }

        public Task getTask() {
            return mTask;
        }
    }
}
