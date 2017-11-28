package com.sun.pd_mvp_clean.tasks.domain.filter;

import com.sun.pd_mvp_clean.tasks.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙毅 on 2017/11/25.
 */

 class InProcessFilter implements TaskFilter {
    @Override
    public List<Task> filter(List<Task> tasks) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isBegin()&& !task.isCompleted()) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
