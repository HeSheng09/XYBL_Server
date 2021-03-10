package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Appeal;
import com.xybl.server.utils.DatetimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AppealDaoTest
 * <p>AppealDao 方法测试类。</p>
 * @author hesheng
 * @create 2021/2/2
 **/
@SpringBootTest(classes = ServerApplication.class)
class AppealDaoTest {
    @Resource
    private AppealDao appealDao;

    private String stu_id;
    private String whu_id;
    private String hs_id;
    private String wh_id;
    private String hb_id;
    private String jyb_id;

    @BeforeEach
    void setUp() {
        stu_id="1615105454044009";
        whu_id="1615123447780013";
        hs_id="1615123277120012";
        wh_id="1615123157287011";
        hb_id="1615123042791010";
        jyb_id="1615015018511100";
    }

    @Test
    void getUnWatchedAppealsByStu_id() {
        List<Appeal> appeals=appealDao.getUnWatchedAppealsByStu_id(stu_id,true);
        for(Appeal appeal:appeals){
            System.out.println(appeal);
        }
    }
}