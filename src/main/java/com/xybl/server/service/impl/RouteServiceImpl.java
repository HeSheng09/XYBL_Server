package com.xybl.server.service.impl;

import com.xybl.server.dao.AppealDao;
import com.xybl.server.dao.ResearchDao;
import com.xybl.server.entity.Appeal;
import com.xybl.server.entity.Research;
import com.xybl.server.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RouteServiceImpl
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/8
 **/
@Service("routeService")
public class RouteServiceImpl implements RouteService {
    @Resource
    private AppealDao appealDao;
    @Resource
    private ResearchDao researchDao;

    @Override
    public Map<String, Object> getOneCompleteRoute(String al_id) {
        Map<String, Object> complete_route = new HashMap<>();
        Map<String, Object> current = getCurRoute(al_id);
        complete_route.put("current", current);
        if (current.get("appeal") != null) {
            Appeal appeal = (Appeal) current.get("appeal");
            List<Map<String, Object>> follows = getFollowRoutes(appeal.getRe_appeal());
            complete_route.put("follows", follows);
        }
        return complete_route;
    }

    @Override
    public Map<String, Object> getCurRoute(String al_id) {
        Map<String, Object> current = new HashMap<>();
        Appeal cur_al = appealDao.getOneAppealById(al_id);
        current.put("appeal", cur_al);
        Research cur_rh = researchDao.getOneResearchByAlId(al_id);
        current.put("research", cur_rh);
        return current;
    }

    @Override
    public List<Map<String, Object>> getFollowRoutes(String re_al) {
        List<Map<String, Object>> follows = new ArrayList<>();
        while (re_al != null) {
            Map<String, Object> cur_route = getCurRoute(re_al);
            if (cur_route.get("appeal") != null) {
                Appeal appeal = (Appeal) cur_route.get("appeal");
                re_al = appeal.getRe_appeal();
            }
            follows.add(cur_route);
        }
        return follows;
    }

    @Override
    public Map<String, Object> getBeforeComRoute(String al_id) {
        // get init appeal id
        String init_al=al_id;
        Appeal appeal= appealDao.getAppealByRl_id(al_id);
        while(appeal!=null){
            init_al=appeal.getId();
            appeal=appealDao.getAppealByRl_id(init_al);
        }
        // get route
        return getOneCompleteRoute(init_al);
    }
}
