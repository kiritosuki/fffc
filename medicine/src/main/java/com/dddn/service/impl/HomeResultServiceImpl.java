package com.dddn.service.impl;

import com.dddn.mapper.PatientMapper;
import com.dddn.pojo.History;
import com.dddn.pojo.PastStatus;
import com.dddn.pojo.Patient;
import com.dddn.pojo.Status;
import com.dddn.service.HomeResultService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeResultServiceImpl implements HomeResultService {
    private final PatientMapper patientMapper;

    public HomeResultServiceImpl(@Qualifier("patientMapper") PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    //查询诊断结果
    @Override
    public Patient selectById(Integer id) {
        //查询基础信息
        Patient patient = patientMapper.selectById(id);
        //查询疾病代号
        List<Integer> leftStatusIllList = patientMapper.selectLeftStatusById(patient.getId());
        List<Integer> rightStatusIllList = patientMapper.selectRightStatusById(patient.getId());
        patient.setLeftStatusIllList(leftStatusIllList);
        patient.setRightStatusIllList(rightStatusIllList);
        //查询医生姓名
        patient.setDoctorName("随便先写一个");
        return patient;
    }

    //将诊断结果添加到数据库
    //本质上是更改
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addDiagResult(Patient patient) {
        //更改患者信息
        patientMapper.updateDiagPatient(patient);
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
        //接下来把这一次诊断的所有信息添加到历史记录
        Patient p = patientMapper.selectById(patient.getId());
        //获取医生姓名
        p.setDoctorName("这是一个医生名字");
        History history = new History(p.getId(), p.getName(), p.getAge(), p.getGender(), p.getPhone(),
                p.getIdCard(), p.getDoctorName(), p.getLeftImg(), p.getRightImg(), p.getLeftDiag(), p.getRightDiag(),
                p.getResInfo(), p.getDiagTime(), p.getCreateTime(), p.getAllergy(), p.getComplaint(), p.getVisit(),
                p.getPresHistory(), p.getPastHistory(), p.getPosFeature(), p.getNegFeature(), p.getLeftStatusIllList(),
                p.getRightStatusIllList(), p.getLeftIllInfo(), p.getRightIllInfo());
        //将病人的基础信息添加到数据库
        patientMapper.addHistory(history);
        //将添加疾病名称代码到历史数据库
        //添加左眼疾病
        patient.getLeftStatusIllList().forEach(ill ->{
            if(ill == 8){
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), patient.getLeftIllInfo(), 1));
            }else{
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), 1));
            }
        });
        //添加右眼疾病
        patient.getRightStatusIllList().forEach(ill ->{
            if(ill == 8){
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), patient.getRightIllInfo(), 2));
            }else{
                patientMapper.addPastStatus(new PastStatus(ill, history.getId(), 2));
            }
        });
    }
}
