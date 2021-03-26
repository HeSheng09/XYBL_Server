package com.xybl.server.service;

import java.util.List;
import java.util.Map;

public interface RouteService {
    /**
    * getOneCompleteRoute
    * <p>根据一条Appeal id获取其直接相关的Appeal、Research和后续相关的所有Appeal、Research。</p>
    * @param al_id java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/8
    */
    public Map<String,Object> getOneCompleteRoute(String al_id);

    /**
    * getCurRoute
    * <p>根据一条Appeal id获取其直接相关的Appeal、Research。</p>
    * @param al_id java.lang.String.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/8
    */
    public Map<String, Object> getCurRoute(String al_id);

    /**
    * getFollowRoutes
    * <p>根据re_appeal字段获取后续相关的所有Appeal、Research。</p>
    * @param re_al java.lang.String.
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @author hesheng
    * @create 2021/3/8
    */
    public List<Map<String, Object>> getFollowRoutes(String re_al);

    /**
     * <p>根据al_id字段获取包括此条举报之前的所有举报和调查。</p>
     * @param al_id java.lang.String
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author hesheng
     * @create 2021/3/26
     */
    public Map<String, Object> getBeforeComRoute(String al_id);
}
