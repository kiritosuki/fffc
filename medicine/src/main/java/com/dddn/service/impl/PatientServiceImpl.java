package com.dddn.service.impl;

import com.dddn.mapper.PatientMapper;
import com.dddn.pojo.History;
import com.dddn.pojo.PageResult;
import com.dddn.pojo.Patient;
import com.dddn.pojo.PatientQueryParam;
import com.dddn.service.PatientService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;

    public PatientServiceImpl(@Qualifier("patientMapper") PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    //病人列表查询 分页查询与条件查询
    @Override
    public PageResult<Patient> getListPage(PatientQueryParam patientQueryParam) {
        //准备分页工作
        PageHelper.startPage(patientQueryParam.getPage(), patientQueryParam.getPageSize());
        //查询病例
        List<Patient> patientList = patientMapper.getListPage(patientQueryParam);
        Page<Patient> p = (Page<Patient>) patientList;
        p.forEach(patient -> {
            patient.setDoctorName("我是一个医生姓名");
        });
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    //根据id删除病人信息和历史信息 支持批量删除
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteByIds(List<Integer> ids) {
        //删除病人的历史疾病编号
        ids.forEach(id -> {
            //查询每个病人的历史记录
            List<History> historyList = patientMapper.selectHistoryById(id);
            if(historyList != null && !historyList.isEmpty()){
                historyList.forEach(history -> {
                    //删掉每个历史记录里面病人的眼部疾病编号
                    patientMapper.deletePastStatus(history.getId());
                });
            }
        });
        //批量删除病人历史信息
        patientMapper.deleteHistoryByIds(ids);
        //删除病人的眼部疾病编号
        ids.forEach(id -> {
            patientMapper.deleteStatus(id);
        });
        //批量删除病人的基本信息
        patientMapper.deleteByIds(ids);
    }

    //病人信息查询回显
    @Override
    public Patient getInfoById(Integer id) {
        //查询基础信息
        Patient patient = patientMapper.selectById(id);
        //查询疾病代号
        List<Integer> leftStatusIllList = patientMapper.selectLeftStatusById(patient.getId());
        List<Integer> rightStatusIllList = patientMapper.selectRightStatusById(patient.getId());
        patient.setLeftStatusIllList(leftStatusIllList);
        patient.setRightStatusIllList(rightStatusIllList);
        //查询异常信息
        String leftIllInfo = patientMapper.getLeftIllInfo(patient.getId());
        String rightIllInfo = patientMapper.getRightIllInfo(patient.getId());
        patient.setLeftIllInfo(leftIllInfo);
        patient.setRightIllInfo(rightIllInfo);
        //查询医生姓名
        patient.setDoctorName("随便先写一个");
        return patient;
    }
}
