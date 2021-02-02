package com.xybl.server.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ResponseUtil
 * <p>封装API调用返回值。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
public class ResponseUtil {

    /**
    * response
    * <p>封装API调用返回值。</p>
    * @param code int. API返回代码。
     * @param msg java.lang.String. API返回信息。
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/2/2
    */
    public static Map<String,Object> response(int code, String msg){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
    * response
    * <p>封装API调用返回值</p>
    * @param code int. API返回代码。
     * @param msg java.lang.String. API返回信息。
     * @param data java.lang.Object. API返回数据。
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/2/2
    */
    public static Map<String,Object> response(int code,String msg,Object data){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data",data);
        return map;
    }
}
