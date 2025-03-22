package com.dddn.controller;

import com.dddn.exception.IdentityException;
import com.dddn.pojo.Patient;
import com.dddn.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.dddn.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;
    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    public HomeController(@Qualifier("homeServiceImpl") HomeService homeService) {
        this.homeService = homeService;
    }

    //添加病人信息
    @PostMapping
    public Result addPatient(@RequestBody Patient patient){
        //根据身份证号查询病人id
        Integer idExist = homeService.selectIdByIdCard(patient.getIdCard());
        //根据身份证号查询病人姓名
        String name = homeService.selectNameByIdCard(patient.getIdCard());
        //如果id不存在 则添加病例
        if(idExist == null){
            homeService.addPatient(patient);
            log.info("添加病人信息: {}", patient);
        }else{
            //如果id存在 若姓名不匹配 则抛出姓名与身份证不符异常
            if(!name.equals(patient.getName())){
                throw new IdentityException("该身份证号已存在, 姓名错误, 身份验证失败! ");
            }else{
                //姓名匹配 则修改病例
                patient.setId(idExist);
                homeService.updatePatient(patient);
                log.info("修改病人信息: {}",patient);
            }
        }
        Integer id = patient.getId();
        return Result.success(id);
    }

    //根据身份证号查询病人信息
    @GetMapping
    public Result getPatient(@RequestParam("idCard") String idCard){
        Patient patient = homeService.getPatient(idCard);
        log.info("根据身份证查询病人信息: {}", patient);
        return Result.success(patient);
    }

}
