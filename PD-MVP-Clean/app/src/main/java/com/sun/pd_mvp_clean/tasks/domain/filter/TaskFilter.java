package com.sun.pd_mvp_clean.tasks.domain.filter;

import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.List;

/**
 * Created by 孙毅 on 2017/11/25.
 * 用于过滤各种状态的tasks
 */

public interface TaskFilter {
    List<Task> filter(List<Task> tasks);
}
