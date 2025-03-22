package com.dddn.controller;

import com.dddn.pojo.Doctor;
import com.dddn.pojo.LoginInfo;
import com.dddn.pojo.Result;
import com.dddn.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginController(@Qualifier("loginServiceImpl") LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public Result login(@RequestBody Doctor doctor){
        log.info("用户尝试登录: {}",doctor);
        LoginInfo loginInfo = loginService.login(doctor);
        return Result.success(loginInfo);
    }
}
