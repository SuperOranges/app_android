package com.sun.ckpt.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/11.
 *   task 即 day_plan
 */

public class taskBean {
    private String taskID;//任务号（日计划号）
    private String Production_order_code;//生产订单号
    private String Products_id;//物料编码
    private int DayCount;//数量
    private String GraphNum;//图号
    private String spec;//规格
    private int procedureKindNo;//工序编号
    private int positionNo;//物料投放点
    private Date beginTime;//开工日期

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getProduction_order_code() {
        return Production_order_code;
    }

    public void setProduction_order_code(String production_order_code) {
        Production_order_code = production_order_code;
    }

    public String getProducts_id() {
        return Products_id;
    }

    public void setProducts_id(String products_id) {
        Products_id = products_id;
    }

    public int getDayCount() {
        return DayCount;
    }

    public void setDayCount(int dayCount) {
        DayCount = dayCount;
    }

    public String getGraphNum() {
        return GraphNum;
    }

    public void setGraphNum(String graphNum) {
        GraphNum = graphNum;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getProcedureKindNo() {
        return procedureKindNo;
    }

    public void setProcedureKindNo(int procedureKindNo) {
        this.procedureKindNo = procedureKindNo;
    }

    public int getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}
