package com.xybl.server.service.impl;

import com.xybl.server.dao.ResearchDao;
import com.xybl.server.dao.UserDao;
import com.xybl.server.entity.NsUser;
import com.xybl.server.entity.Research;
import com.xybl.server.entity.User;
import com.xybl.server.service.ResearchService;
import com.xybl.server.service.UserService;
import com.xybl.server.utils.IDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ResearchServiceImpl
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/5
 **/
@Service("researchService")
public class ResearchServiceImpl implements ResearchService {
    @Resource
    private ResearchDao researchDao;
    @Resource
    private UserDao userDao;

    @Override
    public String genResearchId() {
        Map<String, Object> map = researchDao.getLastId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return IDUtil.genId(last_id);
    }

    @Override
    public boolean addOneResearch(Research research, String al_id) {
        try {
            researchDao.addOneResearch(research);
            researchDao.addRelAlRh(research.getId(), al_id);
        } catch (Exception e) {
            e.printStackTrace();
            deleteOneResearch(research.getId(), research.getHandler());
            return false;
        }
        return true;
    }

    @Override
    public void deleteOneResearch(String rh_id, String user_id) {
        researchDao.deleteRelAlRh(rh_id, user_id);
        researchDao.deleteOneResearchById(rh_id, user_id);
    }

    @Override
    public void selfUpdateResearch(Research research) {
        NsUser nsUser = userDao.getNsUserById(research.getHandler());
        if (!"3".equals(nsUser.getPrivilege().substring(1, 2))) {
            // 权限为0,1,2
//            System.out.println(research);
            researchDao.selfUpdateOneResearch(research);
        }
    }

    @Override
    public void superUpdateResearch(Research research) {
        String level = userDao.getNsUserById(research.getAuditor()).getPrivilege().substring(1, 2);
        if ("0".equals(level) || "1".equals(level)) {
            researchDao.superUpdateOneResearch(research);
        }
    }

    @Override
    public void stuUpdateResearch(String stu_id, String rh_id, String app_comment) {
        researchDao.stuUpdateOneResearch(stu_id, rh_id, app_comment);
    }

    @Override
    public Research getOneResearchById(String rh_id) {
        return researchDao.getOneResearchById(rh_id);
    }
}
