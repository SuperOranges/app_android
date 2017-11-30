package com.sun.pd_mvp_clean.tasks.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.sun.pd_mvp_clean.util.DateUtils;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/17.
 */

public final class Task {

    @NonNull
    private final String mTaskID;//任务号（日计划号）

    @Nullable
    private final String mSID;//排产号

    @Nullable
    private final int mSPDID;//排产工序进度编号

    @NonNull
    private final String mProductionOrderCode;//生产订单号

    @NonNull
    private final String mBranchNo;//班组

    @NonNull
    private final String mProductsID;//物料编码

    @NonNull
    private final String mProductionName;//物料名称

    @Nullable
    private final String mProductionUnit;//物料单位

    @Nullable
    private  final int mDayCount;//数量

    @Nullable
    private  final String mGraphNum;//图号

    @Nullable
    private final String mSpec;//规格

    @NonNull
    private final int mProcedureKindNo;//工序编号

    @NonNull
    private final int mPositionNo;//物料投放点  台位？？？

    @Nullable
    private  final Date mBeginTime;//开工日期

    @Nullable
    private  final String mReportEmployeeNo; //报检员

    @Nullable
    private  final Date mReportTime; //报检时间

    @Nullable
    private final String mReportRemarks; //报检说明

    @Nullable
    private  final String mCheckEmployeeNo; //检验员

    @Nullable
    private  final Date mCheckTime; //检验时间

    @Nullable
    private final String mCheckRemarks; //检验说明

    @Nullable
    private final Date mCompleteTime; //入库时间  就是是完工时间

    @Nullable
    private final String mPSDID; //配送单号

    @Nullable
    private final Date mAcceptTime; //接单时间

    @Nullable
    private final Date mSendTime; //派单时间

    @Nullable
    private final Date mAssignTime; //配单时间

    @Nullable
    private final Date mReceiveTime; //班组收料   班组收料时间


    public Task(@NonNull String mTaskID, String mSID, int mSPDID, @NonNull String mProductionOrderCode,
                @NonNull String mBranchNo, @NonNull String mProductsID, @NonNull String mProductionName,
                String mProductionUnit, int mDayCount, String mGraphNum, String mSpec, @NonNull int mProcedureKindNo,
                @NonNull int mPositionNo, Date mBeginTime, String mReportEmployeeNo, Date mReportTime,
                String mReportRemarks, String mCheckEmployeeNo, Date mCheckTime, String mCheckRemarks,
                Date mCompleteTime, String mPSDID, Date mAcceptTime, Date mSendTime, Date mAssignTime,
                Date mReceiveTime) {

        this.mTaskID = mTaskID;
        this.mSID = mSID;
        this.mSPDID = mSPDID;
        this.mProductionOrderCode = mProductionOrderCode;
        this.mBranchNo = mBranchNo;
        this.mProductsID = mProductsID;
        this.mProductionName = mProductionName;
        this.mProductionUnit = mProductionUnit;
        this.mDayCount = mDayCount;
        this.mGraphNum = mGraphNum;
        this.mSpec = mSpec;
        this.mProcedureKindNo = mProcedureKindNo;
        this.mPositionNo = mPositionNo;
        this.mBeginTime = mBeginTime;
        this.mReportEmployeeNo = mReportEmployeeNo;
        this.mReportTime = mReportTime;
        this.mReportRemarks = mReportRemarks;
        this.mCheckEmployeeNo = mCheckEmployeeNo;
        this.mCheckTime = mCheckTime;
        this.mCheckRemarks = mCheckRemarks;
        this.mCompleteTime = mCompleteTime;
        this.mPSDID = mPSDID;
        this.mAcceptTime = mAcceptTime;
        this.mSendTime = mSendTime;
        this.mAssignTime = mAssignTime;
        this.mReceiveTime = mReceiveTime;

    }

    //   get method

    @Nullable
    public Date getReportTime() {
        return mReportTime;
    }

    @Nullable
    public String getReportRemarks() {
        return mReportRemarks;
    }

    @Nullable
    public String getCheckEmployeeNo() {
        return mCheckEmployeeNo;
    }

    @NonNull
    public String getTaskID() {
        return mTaskID;
    }

    @Nullable
    public String getSID() {
        return mSID;
    }

    @Nullable
    public int getSPDID() {
        return mSPDID;
    }

    @NonNull
    public String getProductionOrderCode() {
        return mProductionOrderCode;
    }

    @NonNull
    public String getBranchNo() {
        return mBranchNo;
    }

    @NonNull
    public String getProductsID() {
        return mProductsID;
    }

    @NonNull
    public String getProductionName() {
        return mProductionName;
    }

    @Nullable
    public String getProductionUnit() {
        return mProductionUnit;
    }

    @Nullable
    public int getDayCount() {
        return mDayCount;
    }

    @Nullable
    public String getGraphNum() {
        return mGraphNum;
    }

    @Nullable
    public String getSpec() {
        return mSpec;
    }

    @NonNull
    public int getProcedureKindNo() {
        return mProcedureKindNo;
    }

    @NonNull
    public int getPositionNo() {
        return mPositionNo;
    }

    @Nullable
    public Date getBeginTime() {
        return mBeginTime;
    }

    @Nullable
    public String getReportEmployeeNo() {
        return mReportEmployeeNo;
    }

    @Nullable
    public Date getCheckTime() {
        return mCheckTime;
    }

    @Nullable
    public String getCheckRemarks() {
        return mCheckRemarks;
    }

    @Nullable
    public Date getCompleteTime() {
        return mCompleteTime;
    }

    @Nullable
    public String getPSDID() {
        return mPSDID;
    }

    @Nullable
    public Date getAcceptTime() {
        return mAcceptTime;
    }

    @Nullable
    public Date getSendTime() {
        return mSendTime;
    }

    @Nullable
    public Date getAssignTime() {
        return mAssignTime;
    }

    @Nullable
    public Date getReceiveTime() {
        return mReceiveTime;
    }

    //  是否开工
    public boolean isBegin(){
        if(mBeginTime != null){
            return true;
        }else{
            return false;
        }
    }

    //  是否报检
    public boolean isReport(){
        if(mReportTime != null){
            return true;
        }else{
            return false;
        }
    }

    //  是否检查
    public boolean isChecked(){
        if(mCheckTime != null){
            return true;
        }else{
            return false;
        }
    }

    //  是否派单
    public boolean isSend(){
        if(mSendTime != null){
            return true;
        }else{
            return false;
        }
    }

    //  是否配单
    public boolean isAssign(){
        if( mAssignTime != null){
            return true;
        }else{
            return false;
        }
    }

    //  是否工料收组
    public boolean isReceive(){
        if( mReceiveTime != null){
            return true;
        }else{
            return false;
        }
    }

    //  是否入库
    public boolean isCompleted(){
        if( mCompleteTime != null){
            return true;
        }else{
            return false;
        }
    }
    //是否正常完成
    public boolean isNormalCompleted(){
        if(mBeginTime!= null && mCompleteTime!=null && DateUtils.isDateSame(mCompleteTime,mBeginTime)){
            return true;
        }else{
            return false;
        }
    }
    //是否超时
    public boolean isOverTime(){
        if(mBeginTime!=null && DateUtils.isDateOneBigger(DateUtils.getSystemDatetime(),mBeginTime)){
            return true;
        }else{
            return false;
        }
    }

    //是否超时完成
    public boolean isOvertimeCompleted(){
        if(mBeginTime!= null && mCompleteTime!=null && DateUtils.isDateOneBigger(mCompleteTime,mBeginTime)){
            return true;
        }else{
            return false;
        }
    }
    //是否超过开工时间三天
    public  boolean isOvertimeThreeDaysUnComplete(){
        if(mBeginTime!= null && DateUtils.isDateOverThreeDays(DateUtils.getSystemDatetime(),mBeginTime)){
            return true;
        }else{
            return false;
        }
    }

    public String taskStatus(){
        if(!isBegin()){
            return "待开工";
        }else if(!isReport()){
            return "待报检";
        }else if(!isChecked()){
            return "待检查";
        }else if(!isAssign()){
            return "待配单";
        }else if(!isSend()){
            return "待派单";
        }else if(!isCompleted()){
            return "待收料";
        }else if(!isCompleted()){
            return  "待入库";
        }else{
            return "已完工";
        }

    }
}
