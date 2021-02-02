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

    @Test
    void getOneAppealById() {
        System.out.println(appealDao.getOneAppealById("2021020217000001"));
    }

    @Test
    void getAppeals() {
        List<Appeal> appeals=appealDao.getAppeals();
        for(Appeal appeal:appeals){
            System.out.println(appeal);
        }
    }

    @Test
    void addOneAppeal() {
        Appeal appeal=new Appeal();
        appeal.setId("2021020217000002");
        appeal.setAl_time(DatetimeUtil.getAndFormatDatetime());
        appeal.setAl_address("湖北 武汉");
        appeal.setAl_abstract("插入测试2");
        appeal.setDetail("插入测试2");
        appeal.setAppellant("920210202000001");
        System.out.println(appeal);
        appealDao.addOneAppeal(appeal);
        List<Appeal> appeals=appealDao.getAppeals();
        for(Appeal app:appeals){
            System.out.println(app);
        }
    }

    @Test
    void updateOneAppeal() {
        Appeal appeal=new Appeal();
        appeal.setId("2021020217000002");
        appeal.setDetail("更新测试: "+DatetimeUtil.getAndFormatDatetime());
        appeal.setAl_address("hubei wuhan");
        appeal.setAcceptor("020200000000001");
        appeal.setProgress(1);
        appeal.setRe_appeal(true);
        appealDao.updateOneAppeal(appeal);
        System.out.println(appealDao.getOneAppealById("2021020217000002"));
    }

    @Test
    void deleteOneAppealById() {
        appealDao.deleteOneAppealById("2021020217000002");
        List<Appeal> appeals=appealDao.getAppeals();
        for(Appeal appeal:appeals){
            System.out.println(appeal);
        }
    }

    @Test
    void all_test(){
        System.out.println("getOneAppealById():");
        getOneAppealById();
        System.out.println("getAppeals():");
        getAppeals();
        System.out.println("addOneAppeal():");
        addOneAppeal();
        System.out.println("updateOneAppeal():");
        updateOneAppeal();
        System.out.println("deleteOneAppealById():");
        deleteOneAppealById();
    }
}