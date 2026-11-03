package com.wangrr.managerment.demos.web.controller;

import com.wangrr.managerment.demos.web.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UpLoadController {
    @PostMapping
    public Result upload(String name, String password, MultipartFile image) throws Exception {
        log.info("文件上传:{},{},{}", name, password, image);
        //获取uuid
        UUID uuid = UUID.randomUUID();
        //获取文件类型
        int lastIndexOf = image.getOriginalFilename().lastIndexOf(".");
        String s = image.getOriginalFilename().substring(lastIndexOf);
        //将文件传到磁盘文件
        image.transferTo(new File("D:\\images\\" + uuid + s));
        return Result.success();
    }
}
