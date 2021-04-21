package com.xybl.server.utils;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    private static final String fileDir = "/usr/wb-server/XYBL_Server/data";

    /**
     * uploadFile
     * <p>上传文件并返回其文件名test</p>
     *
     * @param file org.springframework.web.multipart.MultipartFile.
     * @return java.lang.String
     * @author liubocai
     * @create: 2021-02-25
     */
    public static String uploadFile(MultipartFile file) throws IOException {
//        String filePath="D:\\test\\";
        String filePath = "";
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            filePath = fileDir + originalFilename;
            file.transferTo(new File(filePath));
        }
        return filePath;
    }

    public static List<String> uploadFiles(MultipartFile[] files) throws IOException {
//        String filePath = "D:\\test\\";
        String filePath = "";
        List<String> filePathList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                filePath = fileDir + originalFilename;
                file.transferTo(new File(filePath));
                filePathList.add(originalFilename);
            } else {
                continue;
            }
        }
        return filePathList;
    }


    public static HttpServletResponse loadFileAsResource(String path) throws IOException {
        // path是指想要下载的文件的路径
        File file = new File(path);
        // 获取文件名
        String filename = file.getName();
        // 获取文件后缀名
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        // 将文件写入输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        HttpServletResponse res = null;
        res.reset();
        // 设置response的Header
        res.setCharacterEncoding("UTF-8");
        //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        res.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        // 告知浏览器文件的大小
        res.addHeader("Content-Length", "" + file.length());
        OutputStream outputStream = new BufferedOutputStream(res.getOutputStream());
        res.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();
        return res;
    }


}
