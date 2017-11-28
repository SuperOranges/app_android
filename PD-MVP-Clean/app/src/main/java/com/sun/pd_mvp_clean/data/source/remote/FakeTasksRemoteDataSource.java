package com.sun.pd_mvp_clean.data.source.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.sun.pd_mvp_clean.data.source.TasksDataSource;
import com.sun.pd_mvp_clean.scanstate.domain.model.ScanUpLoadTask;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;
import com.sun.pd_mvp_clean.util.DateUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 孙毅 on 2017/11/17.
 *
 * 此类为虚假计划远程数据源，仅仅作为测试用
 */

public class FakeTasksRemoteDataSource implements TasksDataSource {
    private  static FakeTasksRemoteDataSource INSTANCE;
    private  static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    /**
     *  *          1表示 - 今日计划区域内 - 今日之前tasks
     *             2表示 - 今日计划区域内 - 今日tasks
     *             3表示 - 历史计划区域内 - 今日之前tasks
     *             4表示 - 历史计划区域内 - 今日tasks
     *             5表示 - 历史计划区域内 - 明日tasks
     *             6表示 - 历史计划区域内 - 后日tasks
     */
    private  static final Map<String,Task> TASK_SERVICE_DATA_1;
    private  static final Map<String,Task> TASK_SERVICE_DATA_2;
    private  static final Map<String,Task> TASK_SERVICE_DATA_3;
    private  static final Map<String,Task> TASK_SERVICE_DATA_4;
    private  static final Map<String,Task> TASK_SERVICE_DATA_5;
    private  static final Map<String,Task> TASK_SERVICE_DATA_6;


    /**
     * 对于在今天之前未完成的日计划进行颜色区分：未开工是 - 浅蓝色 - ，开工是 - 蓝色 -，正常完成是 - 绿色 -。
     * 到时间还未点击完成的是 - 黄色 -，超出开工时间3天的是 - 棕色 -，超时完成是 - 橙色 -。
     */
    static {
        TASK_SERVICE_DATA_1 = new LinkedHashMap<>(1);//一个已开工未完成的

        addTask(1,"D20171117001","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-17"),"711110",DateUtils.string2Date("2017-11-17"),
                "aaaaa",null,null,null,null,null,null,null,null,null);

        TASK_SERVICE_DATA_2 = new LinkedHashMap<>(2);//一个未开工，一个已开工未完成的

        addTask(2,"D20171118002","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, null,null,null,
               null,null,null,null,null,null,null,null,null,null);
        addTask(2,"D20171118003","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-18"),"711110",DateUtils.string2Date("2017-11-18"),
                "aaaaa",null,null,null,null,null,null,null,null,null);

        TASK_SERVICE_DATA_3 = new LinkedHashMap<>(3);//一个已开工未完成的，一个超过三天完工的，一个超时完成的

        addTask(3,"D20171117001","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-17"),"711110",DateUtils.string2Date("2017-11-17"),
                "aaaaa",null,null,null,null,null,null,null,null,null);
        addTask(3,"D20171111001","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-11"),"711110",DateUtils.string2Date("2017-11-11"),
                "aaaaa","111111",DateUtils.string2Date("2017-11-12"),"bbbbbb",DateUtils.string2Date("2017-11-15"),"P565482",
                DateUtils.string2Date("2017-11-15"),DateUtils.string2Date("2017-11-15"),DateUtils.string2Date("2017-11-15"),
                DateUtils.string2Date("2017-11-15"));
        addTask(3,"D20171116002","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-11"),"711110",DateUtils.string2Date("2017-11-11"),
                "aaaaa","111111",DateUtils.string2Date("2017-11-12"),"bbbbbb",DateUtils.string2Date("2017-11-13"),"P565482",
                DateUtils.string2Date("2017-11-13"),DateUtils.string2Date("2017-11-13"),DateUtils.string2Date("2017-11-13"),
                DateUtils.string2Date("2017-11-13"));

        TASK_SERVICE_DATA_4 = new LinkedHashMap<>(3);//一个未开工，一个已开工未完成的，一个今日正常完成的
        addTask(4,"D20171118002","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, null,null,null,
                null,null,null,null,null,null,null,null,null,null);
        addTask(4,"D20171118003","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-18"),"711110",DateUtils.string2Date("2017-11-18"),
                "aaaaa",null,null,null,null,null,null,null,null,null);
        addTask(4,"D20171118001","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, DateUtils.string2Date("2017-11-18"),"711110",DateUtils.string2Date("2017-11-18"),
                "aaaaa","111111",DateUtils.string2Date("2017-11-18"),"bbbbbb",DateUtils.string2Date("2017-11-18"),"P565482",
                DateUtils.string2Date("2017-11-18"),DateUtils.string2Date("2017-11-18"),DateUtils.string2Date("2017-11-18"),
                DateUtils.string2Date("2017-11-18"));
        TASK_SERVICE_DATA_5 = new LinkedHashMap<>(1);//一个未开工的明日的计划

        addTask(5,"D20171119001","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, null,null,null,
                null,null,null,null,null,null,null,null,null,null);

        TASK_SERVICE_DATA_6 = new LinkedHashMap<>(1);//一个未开工的后日的计划

        addTask(6,"D20171120001","S20171100101",827,"789456123456789","01100","A010100000000","钢材","吨",
                45,"3324","1147",3,2, null,null,null,
                null,null,null,null,null,null,null,null,null,null);

    }
    public static FakeTasksRemoteDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FakeTasksRemoteDataSource();
        }
        return INSTANCE;
    }
    //阻止直接实例化
    private FakeTasksRemoteDataSource(){
    }

    private static void addTask(@NonNull int flag,@NonNull String mTaskID, String mSID, int mSPDID, @NonNull String mProductionOrderCode,
                                @NonNull String mBranchNo, @NonNull String mProductsID, @NonNull String mProductionName,
                                String mProductionUnit, int mDayCount, String mGraphNum, String mSpec, @NonNull int mProcedureKindNo,
                                @NonNull int mPositionNo, Date mBeginTime, String mReportEmployeeNo, Date mReportTime,
                                String mReportRemarks, String mCheckEmployeeNo, Date mCheckTime, String mCheckRemarks,
                                Date mCompleteTime, String mPSDID, Date mAcceptTime, Date mSendTime, Date mAssignTime,
                                Date mReceiveTime) {
        Task testTaskInfo = new Task(mTaskID,mSID,mSPDID,mProductionOrderCode,mBranchNo,mProductsID,mProductionName,
                mProductionUnit,mDayCount,mGraphNum,mSpec,mProcedureKindNo,mPositionNo,mBeginTime,mReportEmployeeNo,
                mReportTime,mReportRemarks,mCheckEmployeeNo,mCheckTime,mCheckRemarks,mCompleteTime,mPSDID,mAcceptTime,
                mSendTime,mAssignTime,mReceiveTime);
        switch (flag) {
            case 1:
                TASK_SERVICE_DATA_1.put(testTaskInfo.getTaskID(), testTaskInfo);
                break;
            case 2:
                TASK_SERVICE_DATA_2.put(testTaskInfo.getTaskID(), testTaskInfo);
                break;
            case 3:
                TASK_SERVICE_DATA_3.put(testTaskInfo.getTaskID(), testTaskInfo);
                break;
            case 4:
                TASK_SERVICE_DATA_4.put(testTaskInfo.getTaskID(), testTaskInfo);
                break;
            case 5:
                TASK_SERVICE_DATA_5.put(testTaskInfo.getTaskID(), testTaskInfo);
                break;
            case 6:
                TASK_SERVICE_DATA_6.put(testTaskInfo.getTaskID(), testTaskInfo);
                break;
            default:
                break;
        }
    }

    @Override
    public void getTaskById(@NonNull int flag, @NonNull String taskID, @NonNull GetTaskCallback callback) {
        //不需要实现，在TasksRepository中是实现
    }

    @Override
    public void refreshTasks(@NonNull int flag) {
        //不需要实现，在TasksRepository中是实现
    }

    @Override
    public void getUncompletedTasks(@NonNull final int flag, @NonNull Date date, @NonNull final LoadTasksCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (flag) {
                    case 1:
                    callback.onTasksLoaded(Lists.<Task>newArrayList(TASK_SERVICE_DATA_1.values()));
                        break;
                    case 2:
                    callback.onTasksLoaded(Lists.<Task>newArrayList(TASK_SERVICE_DATA_2.values()));
                        break;
                    default:callback.onDataNotAvailable();break;
                }
            }
        },SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getFutureTasks(@NonNull final int flag, @NonNull Date date, @NonNull final LoadTasksCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (flag) {
                    case 4:
                        callback.onTasksLoaded(Lists.<Task>newArrayList(TASK_SERVICE_DATA_4.values()));
                        break;
                    case 5:
                        callback.onTasksLoaded(Lists.<Task>newArrayList(TASK_SERVICE_DATA_5.values()));
                        break;
                    case 6:
                        callback.onTasksLoaded(Lists.<Task>newArrayList(TASK_SERVICE_DATA_6.values()));
                        break;
                    default:callback.onDataNotAvailable();break;
                }
            }
        },SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getBeforeTasks(@NonNull Date date1, @NonNull Date date2, @NonNull final LoadTasksCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTasksLoaded(Lists.<Task>newArrayList(TASK_SERVICE_DATA_3.values()));
            }
        },SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void uploadScanTask(@NonNull ScanUpLoadTask upLoadTask, @NonNull UploadTasksCallback callback) {
        //暂时不实现
    }
}
