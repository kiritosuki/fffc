package com.dddn.mapper;

import com.dddn.pojo.Patient;
import com.dddn.pojo.PatientQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatientMapper {

    //根据条件查询 返回病例列表
    List<Patient> getPatientList(PatientQueryParam patientQueryParam);

    //根据ids删除/批量删除病例 并且删除病例患病情况
    void deleteByIds(List<Integer> ids);
    void deleteStatusName(List<Integer> ids);

    //根据ids获取病人上传的图片
    List<String> getleftImagsByIds(List<Integer> ids);
    List<String> getrightImagsByIds(List<Integer> ids);
}
