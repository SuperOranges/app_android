package com.sun.pd_mvp_clean.data.source;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.List;

/**
 * Created by 孙毅 on 2017/10/17.
 * 访问task数据的主要入口
 */

public interface TasksDataSource {
       interface LoadTasksCallback{
                void onTasksLoaded(List<Task> tasks);
                void onDataNotAvailable();
       }
       interface GetTaskCallback{
                void onTaskLoaded(Task task);
                void onDataNotAvailable();
       }

       //取得所有Task
       void getTasks(@NonNull LoadTasksCallback callback);

        //按ID取得一个Task
        void getTask(@NonNull String taskID,@NonNull GetTaskCallback callback);

        //刷新Task
        void refreshTasks();



}
