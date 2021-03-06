package com.xybl.server.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * IDUtil
 * <p>ID生成工具类。</p>
 *
 * @author hesheng
 * @create 2021/3/1
 **/
public class IDUtil {
    /**
    * genId
    * <p>连接当前mm时间戳和传入的last_id，生成id。</p>
    * @param last_id int.
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/3/1
    */
    public static String genId(int last_id){
        StringBuilder buffer = new StringBuilder();
        buffer.append(System.currentTimeMillis());
        int id = last_id + 1;
        if (id < 10) {
            buffer.append("00").append(id);
        } else if (id < 100) {
            buffer.append("0").append(id);
        } else if (id < 1000) {
            buffer.append(id);
        } else {
            buffer.append("000");
        }
        return buffer.toString();
    }
}
