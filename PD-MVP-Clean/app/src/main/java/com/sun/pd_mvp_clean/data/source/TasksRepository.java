package com.sun.pd_mvp_clean.data.source;

import static com.google.common.base.Preconditions.checkNotNull;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 孙毅 on 2017/10/17.
 * 具体实现将task从数据源加载到缓存中
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksRemoteDataSource;

    //将Tasks保存在缓存中
    Map<String, Task> mCachedTasks;

    //给缓存中的数据标记为无效，以便在下次请求时强制更新
    boolean mCacheIsDirty = false;

    //阻止直接实例化
    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource){
            mTasksRemoteDataSource =checkNotNull(tasksRemoteDataSource);//检查远程数据源引用非空
    }
    //单例模式 ，返回该类的单个实例，仅在必要时才创建它
    public static TasksRepository getINSTANCE(TasksDataSource tasksRemoteDataSource){

        if(INSTANCE == null){
            INSTANCE = new TasksRepository(tasksRemoteDataSource);
        }
        return  INSTANCE;
    }
    //如有必要，在下次被调用时，强制创建一个新的实例
    public static void destoryInstance(){
        INSTANCE = null ;
    }


    //从缓存或者远程数据库中取得Task



    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        checkNotNull(callback);//首先确保回调方法非空引用
        //当缓存中存在且为非脏数据时，立即响应
        if(mCachedTasks != null&&!mCacheIsDirty){
            callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }
        if(mCacheIsDirty){
            getTasksFromRemoteDataSource(callback);
        }
    }


    //从缓存中按taskID取得task，缓存中没有时访问远程数据源
    @Override
    public void getTask(@NonNull final String taskID, @NonNull final GetTaskCallback callback) {
        checkNotNull(taskID);
        checkNotNull(callback);

        Task cacheTask = getTaskWithId(taskID);
        //当缓存中存在，立即响应
        if(cacheTask!=null){
            callback.onTaskLoaded(cacheTask);
            return;
        }
        //如有需要，从服务器加载
        mTasksRemoteDataSource.getTask(taskID, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void refreshTasks() {
        mCacheIsDirty = true ;
    }

    private void getTasksFromRemoteDataSource(@NonNull final LoadTasksCallback callback){
        mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                refreshCache(tasks);
                callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    //刷新缓存中的task
    private void refreshCache(List<Task> tasks){
        if(mCachedTasks == null){
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for(Task task:tasks){
            mCachedTasks.put(task.getmTaskID(),task);
        }
        mCacheIsDirty = false;
    }

    //在缓存中按TaskId获得task
    @Nullable
    private Task getTaskWithId(@NonNull String id ){
        if(mCachedTasks ==null || mCachedTasks.isEmpty()){
            return null;
        }else{
            return mCachedTasks.get(id);
        }
    }
}
