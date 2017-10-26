package com.sun.pd_mvp_clean.scanstate.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/26.
 */

public class ScanUpLoadTask {
    
    //扫码的日计划ID
    @NonNull
    private final String mUpLoadTaskId;
    
    //扫码的日计划时间，来自于网络；断网时来自于系统；
    @NonNull
    private final Date mUpLoadTaskTime;
    
    //扫码者ID？   是通过再一次扫码取得，还是就是登陆者的ID
    @Nullable
    private  final String  mUserID;
    
    //扫码时的备注说明
    @Nullable
    private final String mRemarks;
    
    //// TODO: 2017/10/26  
    //用数字代号来标记当前的日计划进度，以便后续提取处理    ？？？？代号的约定需要再思考;
    @NonNull
    private final int mTaskState;

    //非常详细的这个构造方法用于报检和检验时的扫码和说明
    public ScanUpLoadTask(@NonNull String upLoadTaskId, @NonNull Date upLoadTaskTime, String userID, String remarks,@NonNull int taskState) {
        mUpLoadTaskId = upLoadTaskId;
        mUpLoadTaskTime = upLoadTaskTime;
        mUserID = userID;
        mRemarks = remarks;
        mTaskState = taskState;
    }
    
    //用于派单，配单，工组收料和入库扫码时用        ？？？需要登记派单员，配货员和库管员吗？？？
    public ScanUpLoadTask(@NonNull String upLoadTaskId, @NonNull Date upLoadTaskTime,@NonNull int taskState) {
        mUpLoadTaskId = upLoadTaskId;
        mUpLoadTaskTime = upLoadTaskTime;
        mUserID = null;
        mRemarks = null;
        mTaskState = taskState;
    }
    
    //Get方法
    
    @NonNull
    public String getUpLoadTaskId() {
        return mUpLoadTaskId;
    }

    @NonNull
    public Date getUpLoadTaskTime() {
        return mUpLoadTaskTime;
    }

    @NonNull
    public int getTaskState() {
        return mTaskState;
    }
    
}
