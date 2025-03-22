package com.dddn.mapper;

import com.dddn.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PatientMapper {

    Patient selectById(Integer id);

    List<Integer> selectLeftStatusById(Integer id);

    List<Integer> selectRightStatusById(Integer id);

    void updateDiagPatient(Patient patient);

    void addPatient(Patient patient);

    void addStatus(Status status);

    void deleteStatus(Integer id);

    void addHistory(History history);

    List<Patient> getListPage(PatientQueryParam patientQueryParam);

    void deleteByIds(List<Integer> ids);

    void deleteHistoryByIds(List<Integer> ids);

    Integer selectIdByIdCard(String idCard);

    void addPastStatus(PastStatus pastStatus);

    void updatePatient(Patient patient);

    void updateStatus(Status status);

    String selectNameByIdCard(String idCard);

    void deletePastStatus(Integer id);

    Patient getPatientByPhoneAndIdCard(@Param("idCard") String idCard);

    List<History> selectHistoryById(Integer id);

    String getLeftIllInfo(Integer id);

    String getRightIllInfo(Integer id);
}
