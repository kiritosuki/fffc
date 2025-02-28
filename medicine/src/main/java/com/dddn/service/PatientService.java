package com.dddn.service;

import com.dddn.pojo.PageResult;
import com.dddn.pojo.Patient;
import com.dddn.pojo.PatientQueryParam;

import java.util.List;

public interface PatientService {
    PageResult<Patient> listPage(PatientQueryParam patientQueryParam);

    void delete(List<Integer> ids) throws Exception;
}
