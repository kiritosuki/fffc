package com.dddn.exception;

import com.dddn.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Result HandleException(Exception e){
        log.error("程序出错了",e);
        return Result.error("程序出错了, 请联系后端开发人员~");
    }

/*    @ExceptionHandler
    public Result HandleIllegalArgumentException(IllegalArgumentException e){
        log.error("用户在删除时未指定删除的对象",e);
        String errorMessage = e.getMessage();
        return Result.error(errorMessage);
    }*/

/*    @ExceptionHandler
    public Result HandleDuplicateKeyException(DuplicateKeyException e){
        log.error("用户在添加病例时使用了已存在的身份证号",e);
        String errorMsg = e.getMessage().split("Duplicate entry")[1].split(" ")[1];
        return Result.error(errorMsg + " 已存在");
    }*/

    @ExceptionHandler
    public Result HandleIdentityException(IdentityException e){
        log.error("用户身份验证失败",e);
        String errorMsg = e.getMessage();
        return Result.error(errorMsg);
    }

    @ExceptionHandler
    public Result HandleLoginErrorException(LoginErrorException e){
        log.error("用户名或密码错误",e);
        String errorMsg = e.getMessage();
        return Result.error(errorMsg);
    }
}
