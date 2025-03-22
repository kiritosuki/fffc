package com.dddn.controller;

import com.dddn.pojo.Patient;
import com.dddn.pojo.Result;
import com.dddn.service.HomeResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homeResult")
public class HomeResultController {
    private final static Logger log = LoggerFactory.getLogger(HomeResultController.class);
    private final HomeResultService homeResultService;

    public HomeResultController(@Qualifier("homeResultServiceImpl") HomeResultService homeResultService) {
        this.homeResultService = homeResultService;
    }

    //查询诊断结果
    @GetMapping
    public Result selectById(@RequestParam("id") Integer id){
        log.info("查询诊断结果-患者id: {}", id);
        Patient patient = homeResultService.selectById(id);
        log.info("查询诊断结果: {}", patient);
        return Result.success(patient);
    }

    //将诊断结果添加到数据库
    @PostMapping
    public Result addDiagResult(@RequestBody Patient patient){
        homeResultService.addDiagResult(patient);
        log.info("诊断结果和id信息: {}", patient);
        return Result.success();
    }

}
