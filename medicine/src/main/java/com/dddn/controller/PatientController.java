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
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(@Qualifier("patientServiceImpl") PatientService patientService) {
        this.patientService = patientService;
    }

    //查询病例列表
    @GetMapping
    public Result getListPage(@ModelAttribute PatientQueryParam patientQueryParam){
        PageResult<Patient> pageResult = patientService.getListPage(patientQueryParam);
        log.info("查询病例列表: {}", pageResult);
        return Result.success(pageResult);
    }

    //删除病例
    @DeleteMapping
    public Result deletePatient(@RequestParam("ids") List<Integer> ids){
        patientService.deleteByIds(ids);
        log.info("删除病例-根据ids: {}", ids);
        return Result.success();
    }

    //查询回显
    @GetMapping("/info")
    public Result getInfoById(@RequestParam("id") Integer id){
        log.info("查询病人信息-id: {}", id);
        Patient patient = patientService.getInfoById(id);
        return Result.success(patient);
    }



}
