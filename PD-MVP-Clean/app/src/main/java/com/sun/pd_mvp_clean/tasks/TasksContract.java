package com.sun.pd_mvp_clean.tasks;

import com.sun.pd_mvp_clean.BasePresenter;
import com.sun.pd_mvp_clean.BaseView;

/**
 * Created by 孙毅 on 2017/11/14.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

    }

    interface  Presenter extends BasePresenter {

    }
}
