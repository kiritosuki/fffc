package com.dddn.controller;

import com.dddn.pojo.QuickResult;
import com.dddn.pojo.Result;
import com.dddn.service.QuickDiagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class QuickDiagController {
    private final QuickDiagService quickDiagService;
    private final static Logger log = LoggerFactory.getLogger(QuickDiagController.class);

    public QuickDiagController(@Qualifier("quickDiagServiceImpl") QuickDiagService quickDiagService) {
        this.quickDiagService = quickDiagService;
    }

    @PostMapping("/quickdiag")
    public Result quickDiag(@RequestParam MultipartFile file) throws Exception {
        QuickResult quickResult = quickDiagService.quickDiag(file);
        log.info("快速诊断: {}", quickResult);
        return Result.success(quickResult);
    }
}
