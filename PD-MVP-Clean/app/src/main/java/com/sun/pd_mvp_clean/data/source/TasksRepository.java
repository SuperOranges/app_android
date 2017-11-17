package com.sun.pd_mvp_clean.data.source;

import static com.google.common.base.Preconditions.checkNotNull;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    @Override
    public void getTaskById(@NonNull int flag, @NonNull String taskID, @NonNull GetTaskCallback callback) {
        //先尝试一下从列表点击跳转时把task传过去
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

    private void getTasksFromRemoteDataSource(@NonNull LoadTasksCallback callback){

    }
}
