package com.sun.pd_mvp_clean.data.source;

import static com.google.common.base.Preconditions.checkNotNull;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sun.pd_mvp_clean.scanstate.domain.model.ScanUpLoadTask;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;


import java.util.ArrayList;
import java.util.Date;
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
    Map<String, Task> mUncompletedTodayCachedTasks;//保存今日计划今日部分的未完成的计划

    Map<String, Task> mUncompletedBeforeCacheTasks;//保存今日计划今日之前的未完成计划

    Map<String,Task> mBeforeCacheTasks;//保存历史计划部分今日之前的全部计划

    Map<String,Task> mTodayCacheTasks;//保存历史计划部分今日的全部计划

    Map<String,Task> mTomorrowCacheTasks;//保存历史计划部分明日的全部计划

    Map<String,Task> mAfterTomorrowCacheTasks;//保存历史计划部分后天的全部计划

    //给缓存中的数据标记为无效，以便在下次请求时强制更新
    boolean mUncompletedTodayCacheIsDirty = false;

    boolean mUncompletedBeforeCacheIsDirty = false;

    boolean mBeforeCacheIsDirty = false;

    boolean mTodayCacheIsDirty = false;

    boolean mTomorrowCacheIsDirty = false;

    boolean mAfterTomorrowCacheIsDirty = false;


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

    /**
     *
     * @param flag 此参数用于辨识区块
     * @param taskID
     * @param callback
     */
    @Override
    public void getTaskById(@NonNull int flag, @NonNull String taskID, @NonNull final  GetTaskCallback callback) {
        switch (flag){
            case 1:
                Task cachedTask1 = findTask(taskID,mUncompletedBeforeCacheTasks);
                if (cachedTask1 != null){
                    callback.onTaskLoaded(cachedTask1);
                }else{
                    callback.onDataNotAvailable();
                }
                break;
            case 2:
                Task cachedTask2 = findTask(taskID,mUncompletedTodayCachedTasks);
                if (cachedTask2 != null){
                    callback.onTaskLoaded(cachedTask2);
                }else{
                    callback.onDataNotAvailable();
                }
                break;
            case 3:
                Task cachedTask3 = findTask(taskID,mBeforeCacheTasks);
                if (cachedTask3 != null){
                    callback.onTaskLoaded(cachedTask3);
                }else{
                    callback.onDataNotAvailable();
                }
                break;
            case 4:
                Task cachedTask4 = findTask(taskID,mTodayCacheTasks);
                if (cachedTask4 != null){
                    callback.onTaskLoaded(cachedTask4);
                }else{
                    callback.onDataNotAvailable();
                }
                break;
            case 5:
                Task cachedTask5 = findTask(taskID,mTomorrowCacheTasks);
                if (cachedTask5 != null){
                    callback.onTaskLoaded(cachedTask5);
                }else{
                    callback.onDataNotAvailable();
                }
                break;
            case 6:
                Task cachedTask6 = findTask(taskID,mAfterTomorrowCacheTasks);
                if (cachedTask6 != null){
                    callback.onTaskLoaded(cachedTask6);
                }else{
                    callback.onDataNotAvailable();
                }
                break;
        }
    }

    private Task findTask(@NonNull String taskID, @NonNull Map<String, Task> targetMap){
        checkNotNull(taskID);
        checkNotNull(targetMap);
        if (targetMap == null || targetMap.isEmpty()) {
            return null;
        } else {
            return targetMap.get(taskID);
        }
    }

    //强制刷新缓存中的tasks
    @Override
    public void refreshTasks(@NonNull int flag) {
        checkNotNull(flag);
        switch (flag){
            case 0:
                 mUncompletedTodayCacheIsDirty = true;

                 mUncompletedBeforeCacheIsDirty = true;

                 mBeforeCacheIsDirty = true;

                 mTodayCacheIsDirty = true;

                 mTomorrowCacheIsDirty = true;

                 mAfterTomorrowCacheIsDirty = true;
                break;
            case 1:
                 mUncompletedBeforeCacheIsDirty = true;
                break;
            case 2:
                mUncompletedTodayCacheIsDirty = true;
                break;
            case 3:
                mBeforeCacheIsDirty = true;
                break;
            case 4:
                mTodayCacheIsDirty = true;
                break;
            case 5:
                mTomorrowCacheIsDirty = true;
                break;
            case 6:
                mAfterTomorrowCacheIsDirty = true;
                break;
            default:
                break;
        }

    }

    /**
     *
     * @param flag 模式标志，1表示取得 -今日之前- 未完成的计划，2  表示取得 - 今日 - 未完成的计划
     * @param date 日期，传入的是固定的今日的日期
     * @param callback 回调取得到的tasks
     */
    @Override
    public void getUncompletedTasks(@NonNull final int flag, @NonNull Date date, @NonNull final LoadTasksCallback callback) {
        Log.e("TasksRepository","getUncompletedTasks!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        switch (flag) {
            case 1:
                if(mUncompletedBeforeCacheTasks != null && !mUncompletedBeforeCacheIsDirty ){
                    callback.onTasksLoaded(new ArrayList<Task>(mUncompletedBeforeCacheTasks.values()));
                    return;
                }
                if(mUncompletedBeforeCacheIsDirty) {
                    mTasksRemoteDataSource.getUncompletedTasks(1, date, new LoadTasksCallback() {
                        @Override
                        public void onTasksLoaded(List<Task> tasks) {
                            refreshCache(1, tasks);
                            callback.onTasksLoaded(new ArrayList<Task>(mUncompletedBeforeCacheTasks.values()));
                        }

                        @Override
                        public void onDataNotAvailable() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
                break;
            case 2:
                if(mUncompletedTodayCachedTasks != null && !mUncompletedBeforeCacheIsDirty){
                    callback.onTasksLoaded(new ArrayList<Task>(mUncompletedTodayCachedTasks.values()));
                    return;
                }
                if(mUncompletedTodayCacheIsDirty) {
                    mTasksRemoteDataSource.getUncompletedTasks(2, date, new LoadTasksCallback() {
                        @Override
                        public void onTasksLoaded(List<Task> tasks) {
                            refreshCache(2, tasks);
                            callback.onTasksLoaded(new ArrayList<Task>(mUncompletedTodayCachedTasks.values()));
                        }

                        @Override
                        public void onDataNotAvailable() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
                break;
            default:
                Log.e("TasksRepository","getUncompletedTasksError, flag argument input error!");
                break;
        }
    }

    /**
     *
     * @param flag 模式标志，4表示取得今日的全部计划，5表示取得明日的全部计划，6表示取得后日的全部计划
     * @param date 日期，传入的是固定的今日的日期
     * @param callback  回调取得到的tasks
     */
    @Override
    public void getFutureTasks(@NonNull  int flag, @NonNull Date date, @NonNull final  LoadTasksCallback callback) {
        switch (flag){
            case 4:
                if(mTodayCacheTasks != null && !mTodayCacheIsDirty){
                    Log.e("TasksRepository","mTodayCacheTasks not null !!!!!!!!!!!");
                    callback.onTasksLoaded(new ArrayList<Task>(mTodayCacheTasks.values()));
                    return;
                }
                if(mTodayCacheIsDirty) {
                    Log.e("TasksRepository","mTodayCacheTasks not null !!!!!!!!!!!");
                    mTasksRemoteDataSource.getFutureTasks(4, date, new LoadTasksCallback() {
                        @Override
                        public void onTasksLoaded(List<Task> tasks) {
                            refreshCache(4, tasks);
                            callback.onTasksLoaded(new ArrayList<Task>(mTodayCacheTasks.values()));
                        }

                        @Override
                        public void onDataNotAvailable() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
                break;
            case 5:
                if(mTomorrowCacheTasks!=null && !mTomorrowCacheIsDirty ){
                    callback.onTasksLoaded(new ArrayList<Task>(mTomorrowCacheTasks.values()));
                    return;
                }
                if(mTomorrowCacheIsDirty) {
                    mTasksRemoteDataSource.getFutureTasks(5, date, new LoadTasksCallback() {
                        @Override
                        public void onTasksLoaded(List<Task> tasks) {
                            refreshCache(5, tasks);
                            callback.onTasksLoaded(new ArrayList<Task>(mTomorrowCacheTasks.values()));
                        }

                        @Override
                        public void onDataNotAvailable() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
                break;
            case 6:
                if(mAfterTomorrowCacheTasks!=null && !mAfterTomorrowCacheIsDirty ){
                    callback.onTasksLoaded(new ArrayList<Task>(mAfterTomorrowCacheTasks.values()));
                    return;
                }
                if(mAfterTomorrowCacheIsDirty) {
                    mTasksRemoteDataSource.getFutureTasks(6, date, new LoadTasksCallback() {
                        @Override
                        public void onTasksLoaded(List<Task> tasks) {
                            refreshCache(6, tasks);
                            callback.onTasksLoaded(new ArrayList<Task>(mAfterTomorrowCacheTasks.values()));
                        }

                        @Override
                        public void onDataNotAvailable() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
                break;
            default:
                Log.e("TasksRepository","getFutureTasksError, flag argument input error!");
                break;
        }
    }

    @Override
    public void getBeforeTasks(@NonNull Date date1, @NonNull Date date2, @NonNull final LoadTasksCallback callback) {
        if(mBeforeCacheTasks != null && !mBeforeCacheIsDirty){
            callback.onTasksLoaded(new ArrayList<Task>(mBeforeCacheTasks.values()));
            return;
        }
        if(mBeforeCacheIsDirty) {
            mTasksRemoteDataSource.getBeforeTasks(date1, date2, new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(List<Task> tasks) {
                    refreshCache(3, tasks);
                    callback.onTasksLoaded(new ArrayList<Task>(mBeforeCacheTasks.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
        }
    }

    @Override
    public void uploadScanTask(@NonNull ScanUpLoadTask upLoadTask, @NonNull UploadTasksCallback callback) {
        //暂不实现
    }

    /**
     * 此方法用于在取得新tasks数据后立即刷新本地缓存中的数据
     * @param flag 此参数用来辨识刷新哪一块区域
     *             1表示刷新 - 今日计划区域内 - 今日之前tasks
     *             2表示刷新- 今日计划区域内 - 今日tasks
     *             3表示刷新 - 历史计划区域内 - 今日之前tasks
     *             4表示刷新 - 历史计划区域内 - 今日tasks
     *             5表示刷新 - 历史计划区域内 - 明日tasks
     *             6表示刷新 - 历史计划区域内 - 后日tasks
     *
     * @param tasks 此参数用于放置需要刷新的那一区块的新tasks数据
     */
    private void refreshCache(int flag, List<Task> tasks){
            switch (flag){
                case 1:
                    if(mUncompletedBeforeCacheTasks == null ) {
                        mUncompletedBeforeCacheTasks = new LinkedHashMap<>();
                    }
                    mUncompletedBeforeCacheTasks.clear();
                    for (Task task : tasks) {
                        mUncompletedBeforeCacheTasks.put(task.getTaskID(),task);
                    }
                    mUncompletedBeforeCacheIsDirty = false;
                    break;
                case 2:
                    if( mUncompletedTodayCachedTasks== null){
                        mUncompletedTodayCachedTasks= new LinkedHashMap<>();
                    }
                    mUncompletedTodayCachedTasks.clear();
                    for (Task task : tasks) {
                        mUncompletedTodayCachedTasks.put(task.getTaskID(),task);
                    }
                    mUncompletedTodayCacheIsDirty = false;
                    break;
                case 3:
                    if( mBeforeCacheTasks== null){
                        mBeforeCacheTasks= new LinkedHashMap<>();
                }
                    mBeforeCacheTasks.clear();
                for (Task task : tasks) {
                    mBeforeCacheTasks.put(task.getTaskID(),task);
                }
                    mBeforeCacheIsDirty = false;
                    break;
                case 4:
                    if( mTodayCacheTasks== null){
                        mTodayCacheTasks= new LinkedHashMap<>();
                }
                    mTodayCacheTasks.clear();
                for (Task task : tasks) {
                    mTodayCacheTasks.put(task.getTaskID(),task);
                }
                       mTodayCacheIsDirty = false;
                    break;
                case 5:
                    if( mTomorrowCacheTasks== null){
                        mTomorrowCacheTasks= new LinkedHashMap<>();
                }
                    mTomorrowCacheTasks.clear();
                for (Task task : tasks) {
                    mTomorrowCacheTasks.put(task.getTaskID(),task);
                }
                        mTodayCacheIsDirty = false;
                    break;
                case 6:
                    if( mAfterTomorrowCacheTasks== null){
                        mAfterTomorrowCacheTasks= new LinkedHashMap<>();
                }
                    mAfterTomorrowCacheTasks.clear();
                for (Task task : tasks) {
                    mAfterTomorrowCacheTasks.put(task.getTaskID(),task);
                }
                        mAfterTomorrowCacheIsDirty = false;
                    break;
                default:
                    break;

            }


    }

}
