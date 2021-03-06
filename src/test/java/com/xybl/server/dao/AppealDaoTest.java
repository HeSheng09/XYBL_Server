package com.xybl.server.dao;

import com.xybl.server.ServerApplication;
import com.xybl.server.entity.Appeal;
import com.xybl.server.utils.DatetimeUtil;
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
        System.out.println(appealDao.getOneAppealById("1614603824000000"));
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
        appeal.setId("1614955000234003");
        appeal.setAl_time(DatetimeUtil.getAndFormatDatetime());
        appeal.setAppellant("1614600624790001");
        appeal.setAddress("湖北省武汉市");
        appeal.setPos("114.365818,30.534872");
        appeal.setTitle("插入测试3");
        appeal.setDetail("插入测试3\n"+DatetimeUtil.getAndFormatDatetime());
        appeal.setFilepath("D:/temp/xybl_file_storage");
        appeal.setRe_appeal("");
        appealDao.addOneAppeal(appeal);
    }

    @Test
    void updateOneAppeal() {
        Appeal appeal=new Appeal();
        appeal.setId("1614603824000000");
        Appeal ori_appeal=appealDao.getOneAppealById("1614603824000000");
        appeal.setDetail(ori_appeal.getDetail()+"\n修改测试2\n"+DatetimeUtil.getAndFormatDatetime());
        appeal.setFilepath(ori_appeal.getFilepath()+",D:/temp/xybl_file_storage/test.txt");
        appeal.setRe_appeal("1614604717000001");
        appealDao.updateOneAppeal(appeal);
    }

    @Test
    void deleteOneAppealById() {
//        appealDao.deleteOneAppealById("1614604886000002");
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