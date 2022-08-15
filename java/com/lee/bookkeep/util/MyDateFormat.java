package com.lee.bookkeep.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormat {
    public static String getRecordTime(){
        long curTime = new Date().getTime();
        String strDate = "yyyy年MM月dd日 HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDate);
        return sdf.format(curTime);
    }
}
