package com.dddn.service.impl;

import com.dddn.service.UploadService;
import com.dddn.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {
    private final AliyunOSSOperator aliyunOSSOperator;

    public UploadServiceImpl(@Qualifier("aliyunOSSOperator") AliyunOSSOperator aliyunOSSOperator) {
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    //上传病人眼部图片
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        //上传病人图片 返回url
        return aliyunOSSOperator.uploadFile(file.getBytes(), file.getOriginalFilename());
    }
}
