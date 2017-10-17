package com.sun.pd_mvp_clean.data.source;

import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.List;

/**
 * Created by 孙毅 on 2017/10/17.
 * 访问task数据的主要入口
 */

public interface TasksDataSource {
       interface LoadTasksCallback{
                void onTasksLoaded(List<Task> tasks);
                void onDataNotAvaible();
       }
}
