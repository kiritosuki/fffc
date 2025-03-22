package com.dddn.service;

import com.dddn.pojo.Patient;

public interface HomeResultService {
    Patient selectById(Integer id);

    void addDiagResult(Patient patient);
}
