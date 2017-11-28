package com.sun.pd_mvp_clean.tasks;

/**
 * Created by 孙毅 on 2017/11/25.
 */

public enum TaskFilterType {
    /**
     * 不过滤
     */
    ALL_TASKS,
    /**
     * 未开工
     */
    NOT_BEGIN,
    /**
     * 已开工未完成
     */
    IN_PROCESS,
    /**
     * 已经完成的
     */
    COMPLETED
}
