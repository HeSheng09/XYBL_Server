package com.xybl.server.service.impl;

import com.xybl.server.dao.UserDao;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Student;
import com.xybl.server.entity.User;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.IDUtil;
import com.xybl.server.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public int addOneUser(User user) {
        int isAdd = 200;
        if (userDao.getUserByName(user.getName()) != null) {
            isAdd = 401; //用户已存在
        } else {
            userDao.addOneUser(user);
        }
        return isAdd;
    }

    @Override
    public int addOneStu(Student stu) {
        int isAdd = 200;
        if (userDao.getUserById(stu.getId()) != null) {
            isAdd = 401;//用户已存在
        } else {
            try{
                userDao.addOneUser(new User(stu.getId(), stu.getName(), stu.getPwd(), false));
                userDao.addOneStu(stu);
            }catch (Exception e){
                // 插库出错，删除插入的数据
                userDao.deleteOneStu(stu.getId());
                userDao.deleteOneUser(stu.getId());
                isAdd=500;//系统错误
            }
        }
        return isAdd;
    }

    @Override
    public Map<String, Object> login(String name, String pwd) {
        User user = userDao.getUserByName(name);
        if (user == null) {
            return response(400, "nouser"); //用户不存在
        } else if (MD5Util.validText(pwd, user.getPwd())) {
            return response(200, String.valueOf(user.getRole())); //密码正确
        } else {
            return response(401, "pwderror"); //密码错误
        }
    }

    @Override
    public String genId() {
        Map<String, Object> map = userDao.getLastUserId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return IDUtil.genId(last_id);
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public int addOneNsu(NsUser nsu) {
        int isAdd = 200;
        if(userDao.getUserById(nsu.getId()) != null){
            isAdd = 401; //用户已存在
        }else{
            try{
                userDao.addOneUser(new User(nsu.getId(), nsu.getName(), nsu.getPwd(), true));
                userDao.addOneNsu(nsu);
            }catch (Exception e){
                // 插库出错， 删除插入的数据
                userDao.delOneNsu(nsu.getId());
                userDao.deleteOneUser(nsu.getId());
                isAdd = 500;//系统错误
            }
        }
        return isAdd;
    }

    @Override
    public int delOneUserById(String id) {
        int isDel = 200;
        if(userDao.getUserById(id) != null){
            userDao.deleteOneUser(id);
            if(userDao.getUserById(id).getRole() == true){
                userDao.delOneNsu(id);
            }else{
                userDao.deleteOneStu(id);
            }
            return isDel; //成功删除
        }else{
            return 401;//用户不存在
        }
    }

    @Override
    public String genNsUserName(String authoName, String describe, String level) {
        //1.省级代码
        Map<String, String> provinceCode = new HashMap<>();
        provinceCode.put("北京市", "11");
        provinceCode.put("天津市", "12");
        provinceCode.put("上海市", "31");
        provinceCode.put("重庆市", "50");
        provinceCode.put("河北省", "13");
        provinceCode.put("河南省", "41");
        provinceCode.put("云南省", "53");
        provinceCode.put("辽宁省", "21");
        provinceCode.put("黑龙江省", "23");
        provinceCode.put("湖南省", "43");
        provinceCode.put("安徽省", "34");
        provinceCode.put("山东省", "37");
        provinceCode.put("新疆省", "65");
        provinceCode.put("江苏省", "32");
        provinceCode.put("浙江省", "33");
        provinceCode.put("江西省", "36");
        provinceCode.put("湖北省", "42");
        provinceCode.put("广西省", "45");
        provinceCode.put("甘肃省", "62");
        provinceCode.put("山西省", "14");
        provinceCode.put("内蒙古省", "15");
        provinceCode.put("陕西省", "61");
        provinceCode.put("吉林省", "22");
        provinceCode.put("福建省", "35");
        provinceCode.put("贵州省", "52");
        provinceCode.put("广东省", "44");
        provinceCode.put("青海省", "63");
        provinceCode.put("西藏省", "54");
        provinceCode.put("四川省", "51");
        provinceCode.put("宁夏省", "64");
        provinceCode.put("海南省", "46");
        provinceCode.put("台湾省", "71");
        provinceCode.put("香港", "81");
        provinceCode.put("澳门", "82");

        String result = "";

        String t = authoName.substring(0,2);
        String j = authoName.substring(2,4);
        String x = authoName.substring(4,7);
        String s = authoName.substring(7,10);
        String hasRight = authoName.substring(10,11);
        String p = authoName.substring(11,13);

        if(!"0".equals(hasRight)){ //该账号没有分配账号的权限
            return result;
        }
        if("00".equals(t)){ //教育部0级账号授权省级账号
            result += provinceCode.get(describe); //省2位（用户定）
            result += "00000000"; //市、县、学校为空补8位
        }else if("00".equals(j)){//省级账号授权给市级账号
            result += t; //省2位
            result += describe; //市2位（用户定）
            result += "000000"; //县，学校为空补6位
        }else if("000".equals(x)){//市级账号授权给县级账号
            result += (t + j);//省，市4位
            result += describe;//县3位（用户定）
            result += "000"; //学校为空补3位
        }else if("000".equals(s)){//县级账号授权给学校账号
            result += (t + j + x);//省市县7位
            result += describe;//学校3位（用户定）
        }else if("00".equals(p)){//学校账号授权给个人管理员
            result += (t+j+x+s);//省市县学校10位
        }
        result += level; //权限1位
        if(userDao.getLastNsName(result) == null){//用户2位
            return result + "01";
        }else{
            int lastP = Integer.parseInt(userDao.getLastNsName(result));
            if((lastP+ 1) < 10){
                return result + "0" +String.valueOf(lastP+ 1);
            }else{
                return result + String.valueOf(lastP + 1);
            }
        }
    }
}
