package com.xybl.server.service.impl;

import com.xybl.server.dao.AppealDao;
import com.xybl.server.dao.UserDao;
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
    @Resource
    private UserDao userDao;

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
    public void addOneAppeal(Appeal appeal, String handler) throws Exception {
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
        return appealDao.getAppealsByNsUserId(user_id);
    }

    @Override
    public void updateOneAppealById(Appeal appeal) {
        appealDao.updateOneAppeal(appeal);
    }

    @Override
    public List<Appeal> getAppealsByUserId(String user_id) {
        return appealDao.getAppealsByUser_id(user_id);
    }

    @Override
    public List<Appeal> getUnWatchedAppealsByStu_id(String stu_id) {
        return appealDao.getUnWatchedAppealsByStu_id(stu_id);
    }

    @Override
    public List<Appeal> getUnWatchedAppealsByNs_id(String ns_id) {
        return appealDao.getUnWatchedAppealsByNs_id(ns_id);
    }

    @Override
    public List<Appeal> getWatchedAppealsByStu_id(String user_id) {
        return appealDao.getWatchedAppealsByStu_id(user_id);
    }

    @Override
    public List<Appeal> getNoResultAppealsByStu_id(String user_id) {
        return appealDao.getNoResultAppealsByStu_id(user_id);
    }

    @Override
    public List<Appeal> getHasResultAppealsByStu_id(String user_id) {
        return appealDao.getHasResultAppealsByStu_id(user_id);
    }

    @Override
    public List<Appeal> getRe_appealedAppealsByStu_id(String user_id) {
        return appealDao.getRe_appealedAppealsByStu_id(user_id);
    }

    @Override
    public List<Appeal> getWatchedAppealsByNs_id(String user_id) {
        return appealDao.getWatchedAppealsByNs_id(user_id);
    }

    @Override
    public List<Appeal> getNoResultAppealsByNs_id(String user_id) {
        return appealDao.getNoResultAppealsByNs_id(user_id);
    }

    @Override
    public List<Appeal> getHasResultAppealsByNs_id(String user_id) {
        return appealDao.getHasResultAppealsByNs_id(user_id);
    }

    @Override
    public List<Appeal> searchForAppealsByKeywords(String user_id, String keys) {
        return appealDao.searchForAppealsByKeywords(user_id,keys);
    }
}
