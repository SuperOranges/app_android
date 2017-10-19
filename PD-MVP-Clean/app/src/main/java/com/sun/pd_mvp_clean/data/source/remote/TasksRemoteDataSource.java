package com.sun.pd_mvp_clean.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.data.source.TasksDataSource;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 孙毅 on 2017/10/17.
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private  static TasksRemoteDataSource INSTANCE;
    private  static final int SERVICE_LATENCY_IN_MILLIS = 5000;
    private  static final Map<String,Task> TASK_SERVICE_DATA;

    static {
        TASK_SERVICE_DATA = new LinkedHashMap<>(2);

    }
    public static TasksRemoteDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }
    //阻止直接实例化
    private TasksRemoteDataSource(){

    }



    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskID, @NonNull final GetTaskCallback callback) {
        final Task task = TASK_SERVICE_DATA.get(taskID);
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTaskLoaded(task);
            }
        },SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void refreshTasks() {

    }
}
