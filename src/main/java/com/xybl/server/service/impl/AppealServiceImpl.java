package com.xybl.server.service.impl;

import com.xybl.server.dao.AppealDao;
import com.xybl.server.entity.Appeal;
import com.xybl.server.service.AppealService;
import com.xybl.server.utils.IDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
        Map<String, Object> map = appealDao.getLastId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        return IDUtil.genId(last_id);
    }

    @Override
    public Appeal getOneAppealById(String al_id) {
        return appealDao.getOneAppealById(al_id);
    }

    @Override
    public void addOneAppeal(Appeal appeal, String handler) throws Exception{
        // 保存一条关系到r_al_ns中。
        appealDao.addRelAlHandler(appeal.getId(), handler);
        // 保存举报信息
        appealDao.addOneAppeal(appeal);
    }

    @Override
    public void deleteOneAppealById(String user_id, String al_id) {
        // 删除关系
        appealDao.deleteRelAlNsByAlId(al_id, user_id);
        // 删除举报信息
        appealDao.deleteOneAppealById(al_id, user_id);
    }

    @Override
    public List<Appeal> getAppealsUnderManagement(String user_id) {
        return appealDao.getAppealsByDmSchId(user_id.substring(0, 10) + "000");
    }

    @Override
    public void updateOneAppealById(Appeal appeal) {
        appealDao.updateOneAppeal(appeal);
    }
}
