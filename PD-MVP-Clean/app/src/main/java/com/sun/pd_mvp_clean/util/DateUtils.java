package com.sun.pd_mvp_clean.util;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 孙毅 on 2017/11/17.
 * 用于日期类型转换和获取时间
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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            Log.e("DateUtils","date2date Error!");
            return null;
        }
        return date;
    }

    //获取网络时间  为“yyyy-MM-dd HH:mm:ss”型字符串
    public static Date getWebsiteDatetime(){
        try{
            URL url =new URL("http://www.ntsc.ac.cn");//获取资源对象
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect();//发出连接
            long ld = uc.getDate();//读取网站日期时间
            Date date = new Date(ld);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            return  date2date(date);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Date getSystemDatetime(){

        long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();

        Date d1 =new Date(time);
        return date2date(d1);


    }

}
