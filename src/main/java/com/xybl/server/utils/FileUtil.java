package com.xybl.server.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    public static String saveFile(){
        return null;
    }

}
