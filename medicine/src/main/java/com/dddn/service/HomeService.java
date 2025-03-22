package com.dddn.service;

import com.dddn.pojo.Patient;

public interface HomeService {

    void addPatient(Patient patient);

    Integer selectIdByIdCard(String idCard);

    void updatePatient(Patient patient);

    String selectNameByIdCard(String idCard);

    Patient getPatient(String idCard);
}
