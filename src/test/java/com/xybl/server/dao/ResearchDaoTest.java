package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Research;
import com.xybl.server.utils.DatetimeUtil;
import com.xybl.server.utils.IDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApplication.class)
class ResearchDaoTest {
    @Resource
    private ResearchDao researchDao;

    @Test
    void getOneResearchById() {
        System.out.println(researchDao.getOneResearchById("1614927292653000"));
    }

    @Test
    void getResearches() {
        for (Research research :
                researchDao.getResearches()) {
            System.out.println(research);
        }
    }

    @Test
    void addOneResearch() {
        System.out.println("before:" + researchDao.getResearches().size());
        Research research = new Research();
        research.setId(IDUtil.genId(-1));
        research.setHandler("1612715026000001");
        research.setPro_time(DatetimeUtil.getAndFormatDatetime());
        researchDao.addOneResearch(research);
        System.out.println("after:" + researchDao.getResearches().size());
    }

    @Test
    void deleteOneResearchById() {
        System.out.println("init:" + researchDao.getResearches().size());
        Research research = new Research();
        String id=IDUtil.genId(-1);
        research.setId(id);
        research.setHandler("1612715026000001");
        research.setPro_time(DatetimeUtil.getAndFormatDatetime());
        researchDao.addOneResearch(research);
        System.out.println("add one(id=\""+id+"\"):" + researchDao.getResearches().size());
        researchDao.deleteOneResearchById(id);
        System.out.println("delete one:" + researchDao.getResearches().size());
        System.out.println("select * from t_research where t_rh_id=#{id}:"+researchDao.getOneResearchById(id));
    }

    @Test
    void updateOneResearch() {
        String id="1614927292653000";
        System.out.println("before:"+researchDao.getOneResearchById(id));
        Research research=new Research();
        research.setId(id);
        research.setAuditor("1612717415512000");
        research.setResult("do update test");
        research.setRes_time(DatetimeUtil.getAndFormatDatetime());
        research.setFilepath("d:/temp/xybl_file_storage/research/001.jpg,d:/temp/xybl_file_storage/research/002.txt");
        research.setApp_comment("five stars");
        research.setRe_research("1614928028481000");
        researchDao.updateOneResearch(research);
        System.out.println("after:"+researchDao.getOneResearchById(id));
    }

    @Test
    void getLastId() {
        System.out.println("last_id=:"+researchDao.getLastId());
    }
}