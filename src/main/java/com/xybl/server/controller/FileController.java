package com.xybl.server.controller;

import com.xybl.server.service.AppealService;
import com.xybl.server.service.LogService;
import com.xybl.server.service.ResearchService;
import com.xybl.server.utils.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.xybl.server.utils.ResponseUtil.response;

/**
 * FileController
 * <p></p>
 *
 * @author liubocai
 * @create 2021-04-01
 **/

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private LogService logService;
    @Resource
    private AppealService appealService;
    @Resource
    private ResearchService researchService;

    @RequestMapping("/upload")
    Map<String, Object> upload(@RequestParam(name = "uid", defaultValue = "null")String uid,
                               @RequestParam(name = "alrhid", defaultValue = "null")String alrhid,
                               @RequestParam(name = "alorrh", defaultValue = "null")String alorrh,
                               @RequestParam(name = "uploadFiles") MultipartFile[] uploadFiles) throws IOException {
        //1.存储文件
        List<String> filePaths =  FileUtil.uploadFiles(uploadFiles);
        logService.addOneLog(uid, "upload files", "ok");
        //2.文件名入库
        String aLongStr = " " + String.join(" ", filePaths);
        if("al".equals(alorrh)){
            appealService.uploadFiles(aLongStr, alrhid);
        }
        if("rh".equals(alorrh)){
            researchService.uploadFiles(aLongStr, alrhid);
        }
        return response(200, uid, filePaths);
    }

    @RequestMapping("/download")
    public Map<String, Object> download(@RequestParam(name = "uid")String uid,
                                        @RequestParam(name = "filename")String filename,
                                        HttpServletResponse response) throws UnsupportedEncodingException {
        //1.下载文件
        String filePath = "D:/test/";
        File file = new File(filePath + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logService.addOneLog(uid, "download file"+filename, "ok");
            return response(200, "ok", filename);
        }
        return response(500, "server error");
    }

    @RequestMapping("/getallfile")
    Map<String, Object> getAllFile(@RequestParam("uid")String uid,
                                   @RequestParam("alrhid")String alrhid,
                                   @RequestParam("alorrh")String alorrh){
        //1.查找文件
        String aLongStr = "";
        if("al".equals(alorrh) && appealService.getAllFilesByid(alrhid)!=null){
            aLongStr =  appealService.getAllFilesByid(alrhid);
        }
        else if("rh".equals(alorrh) && researchService.getAllFilesByid(alrhid)!=null){
            aLongStr =  researchService.getAllFilesByid(alrhid);
        }else{
            List<String> strL = new ArrayList<>();
            strL.add("null");
        }
        //2.变为List<String>
        List<String> strL = Arrays.asList(aLongStr.split(" "));
        logService.addOneLog(uid, "getAllFilesByALRHid", "ok");
        return response(200,"ok", strL);
    }
}
