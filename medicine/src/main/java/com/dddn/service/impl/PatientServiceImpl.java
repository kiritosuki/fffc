package com.dddn.service.impl;

import com.dddn.mapper.PatientMapper;
import com.dddn.pojo.PageResult;
import com.dddn.pojo.Patient;
import com.dddn.pojo.PatientQueryParam;
import com.dddn.service.PatientService;
import com.dddn.utils.AliyunOSSOperator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;
    private final AliyunOSSOperator aliyunOSSOperator;

    public PatientServiceImpl(@Qualifier("patientMapper") PatientMapper patientMapper,
                              @Qualifier("aliyunOSSOperator") AliyunOSSOperator aliyunOSSOperator) {
        this.patientMapper = patientMapper;
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    // 分页/条件查询病例列表
    @Override
    public PageResult<Patient> listPage(PatientQueryParam patientQueryParam) {
        //准备对下一次获取的列表分页
        PageHelper.startPage(patientQueryParam.getPage(), patientQueryParam.getPageSize());
        //获取病例列表
        List<Patient> patientList = patientMapper.getPatientList(patientQueryParam);
        //将病人的患病情况由一个String转为List<String>
        patientList.forEach(patient -> {
            String statusName = patient.getStatusName();
            if (statusName.contains(",")) {
                List<String> queryStatusName = Arrays.asList(statusName.split(","));
                patient.setQueryStatusName(queryStatusName);
            } else {
                List<String> singleStatusName = new ArrayList<>();
                singleStatusName.add(statusName);
                patient.setQueryStatusName(singleStatusName);
            }
        });
        //将分页后的列表向下造型为Page
        Page<Patient> p = (Page<Patient>) patientList;
        return new PageResult<Patient>(p.getTotal(), p.getResult());
    }

    //根据ids删除/批量删除病例 并且删除病人的患病信息
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) throws Exception {
        if(ids.isEmpty() || ids == null){
            throw new IllegalArgumentException("请先选择你要删除的员工~");
        }
        //先根据id获取员工的图片名称
        List<String> leftImgList = patientMapper.getleftImagsByIds(ids);
        List<String> rightImgList = patientMapper.getrightImagsByIds(ids);
        //用阿里云工具类在云端删除图片
        if(leftImgList != null && !leftImgList.isEmpty()){
            aliyunOSSOperator.deleteFile(leftImgList);
        }
        if(rightImgList != null && !rightImgList.isEmpty()){
            aliyunOSSOperator.deleteFile(rightImgList);
        }
        //先删除云端图片数据 再删除员工数据 否则员工数据删除后无法删除图片数据
        patientMapper.deleteByIds(ids);
        patientMapper.deleteStatusName(ids);
    }
}
