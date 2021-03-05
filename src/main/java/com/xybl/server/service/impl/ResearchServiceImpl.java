package com.xybl.server.service.impl;

import com.xybl.server.dao.ResearchDao;
import com.xybl.server.service.ResearchService;
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

    @Override
    public String genResearchId() {
        Map<String, Object> map = researchDao.getLastId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return IDUtil.genId(last_id);
    }
}
