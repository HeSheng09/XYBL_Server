package com.xybl.server.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MutilFileUtil
 * <p>用于上传文件存储如后台服务器</p>
 *
 * @author liubocai
 * @create 2021-02-25
 **/
public class FileUtil {

    /**
    * uploadFile
    * <p>上传文件并返回其文件名test</p>
    * @param file org.springframework.web.multipart.MultipartFile.
    * @return java.lang.String
    * @author liubocai
    * @create: 2021-02-25
    */
    public static String uploadFile(MultipartFile file) throws IOException {
        String filePath="D:\\test\\";
        if(!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            filePath += originalFilename;
            file.transferTo(new File(filePath) );
        }
        return filePath;
    }

    public static List<String> uploadFiles(MultipartFile[] files) throws IOException {
        String filePath="D:\\test\\";
        List<String> filePathList = new ArrayList<>();
        for(MultipartFile file:files){
            if(!file.isEmpty()){
                String originalFilename = file.getOriginalFilename();
                filePath += originalFilename;
                file.transferTo(new File(filePath) );
                filePathList.add(filePath);
            }else{
                continue;
            }
        }
        return filePathList;
    }


    public static  byte[] pictest(String filepath) throws IOException{
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while((len = fis.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        System.out.println(bos.toByteArray());
        return bos.toByteArray();
    }


}
