package com.sun.pd_mvp_clean.data.source;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.scanstate.domain.model.ScanUpLoadTask;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.Date;
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
       interface UploadTasksCallback{
                void onTaskUpload(String msg);
                void onDateUp1oadError();
       }

    /**
     * 按ID取得一个Task,用于细节页面，可以先不写，先尝试一下从列表点击跳转时把task传过去
     * @param flag 此参数用于辨识区块
     * @param taskID
     * @param callback
     */
        void getTaskById(@NonNull int flag,@NonNull String taskID,@NonNull GetTaskCallback callback);

        //刷新Task

    /**
     * 此方法用于按不同模式（区块）在下次请求数据时刷新缓存中的Tasks
     * @param flag 模式标志
     *             0表示 - 全部区域内 - 都要刷新
     *             1表示 - 今日计划区域内 - 今日之前tasks
     *             2表示 - 今日计划区域内 - 今日tasks
     *             3表示 - 历史计划区域内 - 今日之前tasks
     *             4表示 - 历史计划区域内 - 今日tasks
     *             5表示 - 历史计划区域内 - 明日tasks
     *             6表示 - 历史计划区域内 - 后日tasks
     */
        void refreshTasks(@NonNull int flag);

    /**
     * 此方法用于获取今日计划内未完成的计划
     * @param flag 模式标志，0表示取得 -今日之前- 未完成的计划，1  表示取得 - 今日 - 未完成的计划
     * @param date 日期，传入的是固定的今日的日期
     * @param callback 回调取得到的tasks
     */
    void getUncompletedTasks(@NonNull int flag,@NonNull Date date,@NonNull LoadTasksCallback callback);

    /**
     * 此方法用于获取今日、明日、后日的 - 全部的 - 的计划，在历史计划页面
     * @param flag 模式标志，0表示取得今日的全部计划，1表示取得明日的全部计划，2表示取得后日的全部计划
     * @param date 日期，传入的是固定的今日的日期
     * @param callback  回调取得到的tasks
     */
    void getFutureTasks(@NonNull int flag,@NonNull Date date,@NonNull LoadTasksCallback callback);

    /**
     * 此方法用于获取今日之前的全部计划，由于计划数众多，故采用日期查找方式，在历史计划页面
     * @param date1  起始日期
     * @param date2  结束日期
     * @param callback  回调取得的tasks
     */
    void getBeforeTasks(@NonNull Date date1,@NonNull Date date2,@NonNull LoadTasksCallback callback);

    /**
     * 此方法用于上传扫码后的task到服务器
     * @param upLoadTask 将扫码必要信息包装到上传对象里
     * @param callback  回调方法用于取得服务器返回的处理信息
     */
    void uploadScanTask(@NonNull ScanUpLoadTask upLoadTask,@NonNull UploadTasksCallback callback);


}
