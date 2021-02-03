package com.xybl.server.utils;

import com.xybl.server.entity.Log;
import com.xybl.server.entity.User;

import java.util.Random;

/**
 * LogUtil
 * <p>日志工具。</p>
 *
 * @author hesheng
 * @create 2021/2/2
 **/
public class LogUtil {
    /**
     * genLogDetail
     * <p>生成日志文本。</p>
     *
     * @param user     com.xybl.server.entity.User. 用户。
     * @param opr      java.lang.String. 对系统进行的操作。
     * @param result   java.lang.String. 操作结果。
     * @param log_time java.lang.String. 时间。
     * @return java.lang.String
     * @author hesheng
     * @create: 2021/2/2
     */
    public static String genLogDetail(User user, String opr, String result, String log_time) {
        //[2021-02-02 21:38:24]  User{920210202000001,测试学生,9}:  生成log文本测试  -->succeed
        return "[" + log_time +
                "]  User{" + user.getId() + "," + user.getName() + "," + user.getRole() + "}:  " +
                opr + "  -->" +
                result;
    }

    /**
    * genLogId
    * <p>生成日志id。id长度为17+15=32.</p>
    * @param log_time java.lang.String. 时间。格式：yyyyMMddHHmmssSSS。有效位数：8+6+3=17.
     * @param userid java.lang.String. 用户id。位数21（取前15位）.
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/2/2
    */
    public static String genLogId(String log_time, String userid) {
        return log_time+ userid.substring(0,15);
    }

    /**
     * genLog
     * <p>生成一条日志。</p>
     *
     * @param user   com.xybl.server.entity.User. 用户。
     * @param opr    java.lang.String. 对系统操作。
     * @param result java.lang.String. 操作结果。
     * @return com.xybl.server.entity.Log
     * @author hesheng
     * @create: 2021/2/2
     */
    public static Log genLog(User user, String opr, String result) {
        Log log = new Log();
        String current = DatetimeUtil.getAndFormatDatetime("yyyyMMddHHmmssSSS");
        log.setId(genLogId(current, user.getId()));
        current=DatetimeUtil.getAndFormatDatetime();
        log.setLog_time(current);
        log.setDetail(genLogDetail(user, opr, result, current));
        return log;
    }
}
