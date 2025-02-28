package com.dddn.controller;

import com.dddn.pojo.PageResult;
import com.dddn.pojo.Patient;
import com.dddn.pojo.PatientQueryParam;
import com.dddn.pojo.Result;
import com.dddn.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    public PatientController(@Qualifier("patientServiceImpl") PatientService patientService) {
        this.patientService = patientService;
    }

    // 分页/条件查询病例列表
    @GetMapping
    public Result listPage(@ModelAttribute PatientQueryParam patientQueryParam) {
        log.info("分页/条件查询-请求数据: {}", patientQueryParam);
        PageResult<Patient> pageResult = patientService.listPage(patientQueryParam);
        return Result.success(pageResult);
    }

    // 删除病例
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        log.info("删除病例-病例id: {}", ids);
        patientService.delete(ids);
        return Result.success();
    }
}
