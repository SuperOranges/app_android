package com.sun.pd_mvp_clean.tasks.domain.filter;

import com.sun.pd_mvp_clean.tasks.TaskFilterType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 孙毅 on 2017/11/25.
 */

public class FilterFactory {
    private static final Map<TaskFilterType, TaskFilter> mFilters = new HashMap<>();

    public FilterFactory() {
        mFilters.put(TaskFilterType.ALL_TASKS, new AllTaskFilter());
        mFilters.put(TaskFilterType.NOT_BEGIN, new NotBeginFilter());
        mFilters.put(TaskFilterType.IN_PROCESS, new InProcessFilter());
        mFilters.put(TaskFilterType.COMPLETED, new CompletedFilter());
    }

    public TaskFilter create(TaskFilterType filterType) {
        return mFilters.get(filterType);
    }
}
