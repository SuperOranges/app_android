package com.sun.pd_mvp_clean.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/10/29.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    public static  final  int DATABASE_VERSION = 1;
    public static  final  String DATABASE_NAME = "User.db";
    private  static  final String TEXT_TYPE = "TEXT";
    private  static  final String BOOLEAN_TYPE = "INTEGER";
    private  static  final String COMMA_SEP = ",";

    private  static  final String SQL_CREATE_ENTRIES = "CREATE TABLE" + UserPersistenceContract.UserEntry.TABLE_NAME +" ("+
            UserPersistenceContract.UserEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
            UserPersistenceContract.UserEntry.COLUMN_NAME_USERID + TEXT_TYPE + COMMA_SEP +
            UserPersistenceContract.UserEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
            UserPersistenceContract.UserEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
            UserPersistenceContract.UserEntry.COLUMN_NAME_FIRSTLOGIN + BOOLEAN_TYPE + " )";

    public UserDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //在版本 1 非必须
    }

    public  void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion ){
        //在版本 1 非必须
    }

}
