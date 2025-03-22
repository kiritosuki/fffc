package com.dddn.controller;

import com.dddn.pojo.Result;
import com.dddn.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    private final UploadService uploadService;
    private final static Logger log = LoggerFactory.getLogger(UploadController.class);

    public UploadController(@Qualifier("uploadServiceImpl") UploadService uploadService) {
        this.uploadService = uploadService;
    }

    //上传病人眼部图片
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam MultipartFile file) throws Exception {
        String url = uploadService.uploadFile(file);
        log.info("上传病人眼部图片-url: {}", url);
        return Result.success(url);
    }
}
