package com.dddn.service.impl;

import com.dddn.mapper.PatientMapper;
import com.dddn.pojo.History;
import com.dddn.pojo.PastStatus;
import com.dddn.pojo.Patient;
import com.dddn.pojo.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.dddn.service.HomeService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    private final PatientMapper patientMapper;

    public HomeServiceImpl(@Qualifier("patientMapper") PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    //根据身份证号查询病人的id是否存在
    @Override
    public Integer selectIdByIdCard(String idCard) {
        return patientMapper.selectIdByIdCard(idCard);
    }

    //根据姓名绑定身份证确认身份
    @Override
    public String selectNameByIdCard(String idCard) {
        return patientMapper.selectNameByIdCard(idCard);
    }

    //根据手机号和身份证号查询病人信息
    @Override
    public Patient getPatient(String idCard) {
        Patient patient = patientMapper.getPatientByPhoneAndIdCard(idCard);
        return patient;
    }


    //添加病人信息
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addPatient(Patient patient) {
        patient.setCreateTime(LocalDateTime.now());
        patient.setUpdateTime(LocalDateTime.now());
        patient.setDiagTime(LocalDate.now());
        //查询医生的姓名 后期完善
        patient.setDoctorName("这是一个医生姓名");
        //机器学习的结果 模拟
        List<Integer> leftStatusIllList = new ArrayList<>();
        leftStatusIllList.add(1);
        List<Integer> rightStatusIllList = new ArrayList<>();
        rightStatusIllList.add(2);
        rightStatusIllList.add(8);
        patient.setLeftStatusIllList(leftStatusIllList);
        patient.setRightStatusIllList(rightStatusIllList);
        //添加病人信息到数据库
        patientMapper.addPatient(patient);
        //添加疾病名称代码到数据库
        //添加左眼信息
        if(leftStatusIllList != null && !leftStatusIllList.isEmpty()){
            leftStatusIllList.forEach(ill -> {
                if(ill == 8){
                    patientMapper.addStatus(new Status(ill, patient.getId(), patient.getLeftIllInfo(), 1));
                }else{
                    patientMapper.addStatus(new Status(ill, patient.getId(), 1));
                }
            });
        }
        //添加右眼信息
        if(rightStatusIllList != null && !rightStatusIllList.isEmpty()){
            rightStatusIllList.forEach(ill -> {
                if(ill == 8){
                    patientMapper.addStatus(new Status(ill, patient.getId(), patient.getRightIllInfo(), 2));
                }else{
                    patientMapper.addStatus(new Status(ill, patient.getId(), 2));
                }
            });
        }
        /*//创建病人的历史信息
        History history = new History(patient.getId(), patient.getName(), patient.getAge(), patient.getGender(), patient.getPhone(),
                patient.getIdCard(), patient.getDoctorName(), patient.getLeftImg(), patient.getRightImg(), patient.getLeftDiag(), patient.getRightDiag(),
                patient.getResInfo(), patient.getDiagTime(), patient.getCreateTime(), patient.getAllergy(), patient.getComplaint(), patient.getVisit(),
                patient.getPresHistory(), patient.getPastHistory(), patient.getPosFeature(), patient.getNegFeature(), patient.getLeftStatusIllList(),
                patient.getRightStatusIllList(), patient.getLeftIllInfo(), patient.getRightIllInfo());
        //将病人的历史信息添加到数据库
        patientMapper.addHistory(history);
        //添加疾病名称代码到历史数据库
        //添加左眼信息
        if(leftStatusIllList != null && !leftStatusIllList.isEmpty()){
            leftStatusIllList.forEach(ill -> {
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), patient.getLeftIllInfo(), 1));
            });
        }
        //添加右眼信息
        if(rightStatusIllList != null && !rightStatusIllList.isEmpty()){
            rightStatusIllList.forEach(ill -> {
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), patient.getRightIllInfo(), 2));
            });
        }*/

    }

    //修改病人信息
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePatient(Patient patient) {
        //修改更新时间
        patient.setUpdateTime(LocalDateTime.now());
        patient.setDiagTime(LocalDate.now());
        //查询医生的姓名 后期完善
        patient.setDoctorName("这是一个医生姓名");
        //机器学习的结果 模拟
        List<Integer> leftStatusIllList = new ArrayList<>();
        leftStatusIllList.add(1);
        List<Integer> rightStatusIllList = new ArrayList<>();
        rightStatusIllList.add(2);
        rightStatusIllList.add(8);
        patient.setLeftStatusIllList(leftStatusIllList);
        patient.setRightStatusIllList(rightStatusIllList);
        //更新病人信息
        patientMapper.updatePatient(patient);
        /*//更改疾病名称代码到数据库
        //更新左眼信息
        if(leftStatusIllList != null && !leftStatusIllList.isEmpty()){
            leftStatusIllList.forEach(ill -> {
                patientMapper.updateStatus(new Status(ill, patient.getId(), patient.getLeftIllInfo(), 1));
            });
        }
        //更新右眼信息
        if(rightStatusIllList != null && !rightStatusIllList.isEmpty()){
            rightStatusIllList.forEach(ill -> {
                patientMapper.updateStatus(new Status(ill, patient.getId(), patient.getRightIllInfo(), 2));
            });
        }*/

        //更改疾病编号 先删除再添加
        //删除疾病编号
        patientMapper.deleteStatus(patient.getId());
        //添加左眼疾病
        patient.getLeftStatusIllList().forEach(ill ->{
            if(ill == 8){
                patientMapper.addStatus(new Status(ill, patient.getId(), patient.getLeftIllInfo(), 1));
            }else{
                patientMapper.addStatus(new Status(ill, patient.getId(), 1));
            }
        });
        //添加右眼疾病
        patient.getRightStatusIllList().forEach(ill ->{
            if(ill == 8){
                patientMapper.addStatus(new Status(ill, patient.getId(), patient.getRightIllInfo(), 2));
            }else{
                patientMapper.addStatus(new Status(ill, patient.getId(), 2));
            }
        });

        /*//创建病人的历史信息
        History history = new History(patient.getId(), patient.getName(), patient.getAge(), patient.getGender(), patient.getPhone(),
                patient.getIdCard(), patient.getDoctorName(), patient.getLeftImg(), patient.getRightImg(), patient.getLeftDiag(), patient.getRightDiag(),
                patient.getResInfo(), patient.getDiagTime(), patient.getCreateTime(), patient.getAllergy(), patient.getComplaint(), patient.getVisit(),
                patient.getPresHistory(), patient.getPastHistory(), patient.getPosFeature(), patient.getNegFeature(), patient.getLeftStatusIllList(),
                patient.getRightStatusIllList(), patient.getLeftIllInfo(), patient.getRightIllInfo());
        //将病人的历史信息添加到数据库
        patientMapper.addHistory(history);
        //添加疾病名称代码到历史数据库
        //添加左眼信息
        if(leftStatusIllList != null && !leftStatusIllList.isEmpty()){
            leftStatusIllList.forEach(ill -> {
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), patient.getLeftIllInfo(), 1));
            });
        }
        //添加右眼信息
        if(rightStatusIllList != null && !rightStatusIllList.isEmpty()){
            rightStatusIllList.forEach(ill -> {
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), patient.getRightIllInfo(), 2));
            });
        }*/
    }




}
