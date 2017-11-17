package com.sun.pd_mvp_clean.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 孙毅 on 2017/11/17.
 */

public class DateUtils {
    private DateUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    //第一个日期大"yyyy-mm-dd"型
    public static boolean isDateOneBigger(Date dateOne,Date dateTwo){
        Date one = date2date(dateOne);
        Date two = date2date(dateTwo);
        if(one.getTime()>two.getTime()){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isDateOverThreeDays(Date dateOne,Date dateTwo) {
        Date one = date2date(dateOne);
        Date two = date2date(dateTwo);
        if ((one.getTime() - two.getTime()) > (3 * 24 * 60 * 60 * 1000 - 1) ) {
            Log.e("DateUtils","DateOverThreeDays!");
            return true;
        }else{
            return false;
        }
    }
    //日期相同"yyyy-mm-dd"型
    public static boolean isDateSame(Date dateOne,Date dateTwo){
        Date one = date2date(dateOne);
        Date two = date2date(dateTwo);
        if (one.equals(two)){
            return true;
        }else {
            return false;
        }
    }
    //字符串转日期"yyyy-mm-dd"型
    public static Date string2Date(String dateStr){
        Date formateDate = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            formateDate = format.parse(dateStr);
        } catch (ParseException e) {
            Log.e("DateUtils","string2Date Error!");
            return null;
        }
        return formateDate;

    }
    //日期格式化"yyyy-mm-dd"型
    public static Date date2date(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            Log.e("DateUtils","date2date Error!");
            return null;
        }
        return date;
    }



}
