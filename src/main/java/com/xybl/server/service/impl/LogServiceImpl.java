package com.xybl.server.service.impl;

import com.xybl.server.dao.LogDao;
import com.xybl.server.entity.Log;
import com.xybl.server.entity.User;
import com.xybl.server.service.LogService;
import com.xybl.server.utils.DatetimeUtil;
import com.xybl.server.utils.IDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * LogServiceImpl
 * <p></p>
 *
 * @author hesheng
 * @create 2021/2/3
 **/
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogDao logDao;

    @Override
    public void addOneLog(String user_id, String opr, String result) {
        Map<String, Object> map = logDao.getLastId();
        int last_id = -1;
        if (map != null) {
            last_id = Integer.parseInt(map.get("last_id").toString());
        }
        //[2021-02-02 21:38:24]  User(920210202000001):  生成log文本测试  -->succeed
        String log_detail = "[" + DatetimeUtil.getAndFormatDatetime() +
                "]  User(" + user_id + "):  " +
                opr + "  -->" +
                result;
        logDao.addOneLog(new Log(IDUtil.genId(last_id), log_detail));
    }
}
