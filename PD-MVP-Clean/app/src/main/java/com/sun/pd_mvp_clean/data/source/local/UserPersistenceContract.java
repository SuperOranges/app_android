package com.sun.pd_mvp_clean.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2017/10/29.
 */

public final class UserPersistenceContract {
    //为防止不小心实例化本类，特设置私有的空的构造函数
    private UserPersistenceContract(){}


    //定义表内容的内部类
    public static abstract  class  UserEntry implements BaseColumns{
        public static  final String TABLE_NAME = "user";
        public static  final String COLUMN_NAME_USERID ="userid";
        public static  final String COLUMN_NAME_PASSWORD ="password";
        public static  final String COLUMN_NAME_USERNAME ="username";
        public static  final String COLUMN_NAME_FIRSTLOGIN ="firstlogin";
    }

}
