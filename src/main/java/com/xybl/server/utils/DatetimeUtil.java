package com.xybl.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DatetimeUtil
 * <p>工具类。用于获取并格式化当前系统时间。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class DatetimeUtil {

    /**
    * getAndFormatDatetime
    * <p>获取并格式化当前系统时间。<br>
     *     时间格式为：yyyy-MM-dd HH:mm:ss</p>
    * @param  .
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/2/2
    */
    public static String getAndFormatDatetime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }
}
