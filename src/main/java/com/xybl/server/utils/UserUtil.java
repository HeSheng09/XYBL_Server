package com.xybl.server.utils;

import com.xybl.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * UserUtil
 * <p></p>
 *
 * @author hesheng
 * @create 2021/2/3
 **/
public class UserUtil {

    private static final List<String> zones = Arrays.asList("北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省",
            "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省",
            "广东省", "广西壮族自治区", "海南省", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省",
            "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区", "台湾省", "unknown");
    private static final List<String> roles = Arrays.asList("系统管理员", "教育部", "省级", "市级", "县级", "学校", "普通用户", "其他用户", "other", "测试");

    /**
     * genUserId
     * <p>生成UserId。</p>
     *
     * @param role    int. 用户角色编号（0-9）。
     * @param zone    int. 注册区域（省级）编号（1-34）。
     * @param last_id int. 当日最近的一个注册用户id。
     * @return java.lang.String
     * @author hesheng
     * @create: 2021/2/3
     */
    public static String genUserId(int role, int zone, int last_id) {
        String id = "" + role + DatetimeUtil.getAndFormatDatetime("yyyyMMddHHmmss");
        if (zone < 10) {
            id = id + "0" + zone;
        } else {
            id = id + zone;
        }
        int current = last_id + 1;
        if (current < 10) {
            id = id + "000" + current;
        } else if (current < 100) {
            id = id + "00" + current;
        } else if (current < 1000) {
            id = id + "0" + current;
        } else {
            id = id + current;
        }
        return id;
    }

    /**
     * genUserId
     * <p>生成UserID。</p>
     *
     * @param role    int. 用户角色编号（0-9）。
     * @param zone    java.lang.String. 注册区域（省级）。
     * @param last_id int. 当日最近的一个注册用户id。
     * @return java.lang.String 若区域检索失败，将返回"no such zone"。
     * @author hesheng
     * @create: 2021/2/3
     */
    public static String genUserId(int role, String zone, int last_id) {
        int idx = zones.indexOf(zone) + 1;
        if (idx == 0) {
            return "no such zone";
        } else {
            return genUserId(role, idx, last_id);
        }
    }

    /**
     * genUserId
     * <p>生成UserID。</p>
     *
     * @param role    java.lang.String. 用户角色。
     * @param zone    int. 注册区域（省级）编号（1-34）。
     * @param last_id int. 当日最近的一个注册用户id。
     * @return java.lang.String 若角色检索失败，将返回"no such role"。
     * @author hesheng
     * @create: 2021/2/3
     */
    public static String genUserId(String role, int zone, int last_id) {
        int idx = roles.indexOf(role);
        if (idx == -1) {
            return "no such role";
        } else {
            return genUserId(idx, zone, last_id);
        }
    }

    /**
     * genUserId
     * <p>生成UserID。</p>
     *
     * @param role    java.lang.String. 用户角色。
     * @param zone    java.lang.String. 注册区域（省级）。
     * @param last_id int. 当日最近的一个注册用户id。
     * @return java.lang.String 若角色或区域检索失败，将返回"no such role or zone"。
     * @author hesheng
     * @create: 2021/2/3
     */
    public static String genUserId(String role, String zone, int last_id) {
        int role_idx = roles.indexOf(role);
        int zone_idx = zones.indexOf(zone) + 1;
        if (role_idx == -1 || zone_idx == 0) {
            return "no such role or zone";
        } else {
            return genUserId(role_idx, zone_idx, last_id);
        }
    }
}
