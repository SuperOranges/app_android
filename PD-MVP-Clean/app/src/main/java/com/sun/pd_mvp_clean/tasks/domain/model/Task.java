package com.sun.pd_mvp_clean.tasks.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

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
    private final Date mBeginTime;//开工日期

    @Nullable
    private final String mReportEmployeeNo;//报检员

    @Nullable
    private final Date mCheckTime;//报检时间

    @Nullable
    private final String mCheckRemarks;//检验说明

    @Nullable
    private final Date mCompleteTime;//入库时间  ？？？？是完工时间吗？？？

    @Nullable
    private final String mPSDID;//配送单号

    @Nullable
    private final Date mAcceptTime;//接单时间

    @Nullable
    private final Date mSendTime;//派单时间

    @Nullable
    private final Date mAssignTime;//配单时间

    @Nullable
    private final Date mReceiveTime;//班组收料


    //一个全体变量的构造方法
    public Task(@NonNull String mTaskID, String mSID, int mSPDID, @NonNull String mProductionOrderCode,
                @NonNull String mBranchNo, @NonNull String mProductsID, @NonNull String mProductionName,
                String mProductionUnit, int mDayCount, String mGraphNum, String mSpec, @NonNull int mProcedureKindNo,
                @NonNull int mPositionNo, Date mBeginTime, String mReportEmployeeNo, Date mCheckTime,
                String mCheckRemarks, Date mCompleteTime, String mPSDID, Date mAcceptTime, Date mSendTime,
                Date mAssignTime, Date mReceiveTime) {

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
        this.mCheckTime = mCheckTime;
        this.mCheckRemarks = mCheckRemarks;
        this.mCompleteTime = mCompleteTime;
        this.mPSDID = mPSDID;
        this.mAcceptTime = mAcceptTime;
        this.mSendTime = mSendTime;
        this.mAssignTime = mAssignTime;
        this.mReceiveTime = mReceiveTime;
    }


}