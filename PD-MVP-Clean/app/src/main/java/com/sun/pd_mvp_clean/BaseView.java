package com.sun.pd_mvp_clean;

/**
 * Created by 孙毅 on 2017/10/16.
 * 基础视图接口，用于设置特定的主持人对象
 */

public interface BaseView<T extends BasePresenter> {
        void setPresenter(T presenter);
}
