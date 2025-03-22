package com.dddn.service.impl;

import com.dddn.pojo.QuickResult;
import com.dddn.service.QuickDiagService;
import com.dddn.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class QuickDiagServiceImpl implements QuickDiagService {
    private final AliyunOSSOperator aliyunOSSOperator;

    public QuickDiagServiceImpl(@Qualifier("aliyunOSSOperator") AliyunOSSOperator aliyunOSSOperator) {
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    @Override
    public QuickResult quickDiag(MultipartFile file) throws Exception {
        String url = aliyunOSSOperator.uploadFile(file.getBytes(), file.getOriginalFilename());
        String diag = "眼部关键字";
        String resInfo = "诊断结果";
        QuickResult quickResult = new QuickResult(url, diag, resInfo, LocalDateTime.now());
        return quickResult;
    }
}
