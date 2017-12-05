package com.sun.pd_mvp_clean.taskdetail;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;
import com.sun.pd_mvp_clean.tasks.TasksContract;
import com.sun.pd_mvp_clean.tasks.domain.model.Task;

/**
 * Created by Administrator on 2017/10/26.
 */

public interface TaskDetailContract {
    interface view extends BaseView<Presenter>{
        void showTaskDetailInfo(Task task);
        void showErrorInfo();

    }
    interface  Presenter extends BasePresenter {
        void getTaskDetail(int flag);
    }
}
