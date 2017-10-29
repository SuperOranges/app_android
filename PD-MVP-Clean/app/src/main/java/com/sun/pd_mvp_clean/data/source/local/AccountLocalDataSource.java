package com.sun.pd_mvp_clean.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.account.domain.model.User;
import com.sun.pd_mvp_clean.data.source.AccountDataSource;
import com.sun.pd_mvp_clean.data.source.local.UserPersistenceContract.UserEntry;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/20.
 */

public class AccountLocalDataSource implements AccountDataSource {

    private static  AccountLocalDataSource INSTANCE;
    private UserDbHelper mUserDbHelper;

    // 防止直接初始化
    private AccountLocalDataSource(@NonNull Context context){
        checkNotNull(context);
        mUserDbHelper = new UserDbHelper(context);
    }

    public static AccountLocalDataSource getInstance(@NonNull Context context){
        if(INSTANCE == null){
            INSTANCE = new AccountLocalDataSource(context);
        }
        return INSTANCE;
    }
    /**
     * Note: {@link LoadUserCallback#onDataNotAvailable()} is fired if the database doesn't exist
     * or the table is empty.
     */
    @Override
    public void findUser(@NonNull LoadUserCallback callback) {
        List<User> users = new ArrayList<User>();
        SQLiteDatabase db = mUserDbHelper.getReadableDatabase();
        String[] projection = {
                UserEntry.COLUMN_NAME_USERID,
                UserEntry.COLUMN_NAME_PASSWORD,
                UserEntry.COLUMN_NAME_USERNAME,
                UserEntry.COLUMN_NAME_FIRSTLOGIN,
        };
        Cursor c = db.query(UserEntry.TABLE_NAME,projection,null,null,null,null,null);
        if(c!=null&&c.getCount()>0){
            while (c.moveToNext()){
                String userID = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_USERID));
                String password = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PASSWORD));
                String userName = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_USERNAME));
                boolean firstLogin = c.getInt(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_FIRSTLOGIN))==1;

                User user = new User(userID,password,userName,firstLogin);
                users.add(user);
            }
        }
        if(c != null){
            c.close();
        }
        db.close();
        if(users.isEmpty()){
            //无数据
            callback.onDataNotAvailable();
        }else{
            callback.onUserLoaded(users.get(0));
        }
    }

    @Override
    public void verifyUser(@NonNull User user, @NonNull LoadUserCallback callback) {
        //在本地数据源中不需要操作这一项
    }

    //保存验证过的用户数据
    @Override
    public void saveUser(@NonNull User user) {
        checkNotNull(user);
        SQLiteDatabase db =mUserDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME_USERID, user.getUserId());
        values.put(UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(UserEntry.COLUMN_NAME_USERNAME, user.getUserName());
        values.put(UserEntry.COLUMN_NAME_FIRSTLOGIN, user.isFirstLogin());

        db.insert(UserEntry.TABLE_NAME, null, values);

        db.close();
    }

    //删除本地全部用户数据
    @Override
    public void deleteUser() {
        SQLiteDatabase db = mUserDbHelper.getWritableDatabase();

        db.delete(UserEntry.TABLE_NAME, null, null);

        db.close();
    }
}
