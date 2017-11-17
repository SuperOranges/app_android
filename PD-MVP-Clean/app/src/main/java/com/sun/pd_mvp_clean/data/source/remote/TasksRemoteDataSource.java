package com.sun.pd_mvp_clean.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.data.source.TasksDataSource;
import com.sun.pd_mvp_clean.scanstate.domain.model.ScanUpLoadTask;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.Date;
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
    public void getTaskById(@NonNull int flag, @NonNull String taskID, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void refreshTasks(@NonNull int flag) {

    }

    @Override
    public void getUncompletedTasks(@NonNull int flag, @NonNull Date date, @NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getFutureTasks(@NonNull int flag, @NonNull Date date, @NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getBeforeTasks(@NonNull Date date1, @NonNull Date date2, @NonNull LoadTasksCallback callback) {

    }

    @Override
    public void uploadScanTask(@NonNull ScanUpLoadTask upLoadTask, @NonNull UploadTasksCallback callback) {

    }
}
