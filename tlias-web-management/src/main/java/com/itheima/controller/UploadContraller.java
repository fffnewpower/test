package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOssOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadContraller {
    /*
//    * 上传文件
//    * 只能使用post请求方法
//    * 上传到本地
//    * */
//    private static final String UPLOAD_DIR="D:\\javalogging\\image\\";
//
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age ,@RequestParam("file") MultipartFile file) throws IOException {
//        log.info("上传文件：{},{},{}",name,age,file);
//        String fileName = file.getOriginalFilename();
//        String fileNameExt = fileName.substring(fileName.lastIndexOf("."));
//        String UUIDFileName = UUID.randomUUID().toString()+fileNameExt;
//
//        File fileTarget = new File(UPLOAD_DIR+UUIDFileName);
//
//        if(!fileTarget.getParentFile().exists()){
//            fileTarget.getParentFile().mkdir();
//        }
//        file.transferTo(fileTarget);
//
//        return Result.success();
//    }


    /*
    * 上传文件到阿里云
    * */
    @Autowired
    private AliyunOssOperator aliyunOssOperator;
    @PostMapping("/upload")
    public Result upload(String name, Integer age ,@RequestParam("file") MultipartFile file) throws IOException, ClientException {
       String url = aliyunOssOperator.uploadAlibabaOss(file.getBytes(), file.getOriginalFilename());
        log.info("图片的url为:"+url);
        return Result.success(url);
    }
}
