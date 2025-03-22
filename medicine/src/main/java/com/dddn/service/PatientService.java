package com.dddn.service;

import com.dddn.pojo.PageResult;
import com.dddn.pojo.Patient;
import com.dddn.pojo.PatientQueryParam;

import java.util.List;

public interface PatientService {
    PageResult<Patient> getListPage(PatientQueryParam patientQueryParam);

    void deleteByIds(List<Integer> ids);

    Patient getInfoById(Integer id);
}
