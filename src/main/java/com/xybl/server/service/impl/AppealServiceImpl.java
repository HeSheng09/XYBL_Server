package com.xybl.server.service.impl;

import com.xybl.server.dao.AppealDao;
import com.xybl.server.service.AppealService;
import com.xybl.server.utils.IDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * AppealServiceImpl
 * <p></p>
 *
 * @author hesheng
 * @create 2021/3/1
 **/
@Service("appealService")
public class AppealServiceImpl implements AppealService {
    @Resource
    private AppealDao appealDao;

    @Override
    public String genAppealId() {
        return IDUtil.genId(getLastId());
    }

    @Override
    public int getLastId() {
        Map<String, Object> map = appealDao.getLastId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return last_id;
    }
}
