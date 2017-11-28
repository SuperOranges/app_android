package com.sun.pd_mvp_clean.tasks.domain.filter;

import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙毅 on 2017/11/25.
 */

 class AllTaskFilter implements TaskFilter {
    @Override
    public List<Task> filter(List<Task> tasks) {
        return new ArrayList<>(tasks);
    }
}
