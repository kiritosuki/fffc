package com.dddn.service.impl;

import com.dddn.exception.LoginErrorException;
import com.dddn.mapper.DoctorMapper;
import com.dddn.pojo.Doctor;
import com.dddn.pojo.LoginInfo;
import com.dddn.service.LoginService;
import com.dddn.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    private final DoctorMapper doctorMapper;

    public LoginServiceImpl(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    public LoginInfo login(Doctor doctor) {
        Doctor loginDoctor = doctorMapper.getByUsernameAndPassword(doctor);
        if(loginDoctor == null){
            throw new LoginErrorException("用户名或密码错误! ");
        }
        //登录成功生成jwt令牌
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", loginDoctor.getId());
        dataMap.put("username", loginDoctor.getUsername());
        String jwt = JwtUtils.generateJwt(dataMap);
        LoginInfo loginInfo = new LoginInfo(loginDoctor.getId(), loginDoctor.getUsername(), loginDoctor.getPassword(), jwt);
        return loginInfo;
    }
}
