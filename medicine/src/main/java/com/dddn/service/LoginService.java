package com.dddn.service;

import com.dddn.pojo.Doctor;
import com.dddn.pojo.LoginInfo;

public interface LoginService {
    LoginInfo login(Doctor doctor);
}
