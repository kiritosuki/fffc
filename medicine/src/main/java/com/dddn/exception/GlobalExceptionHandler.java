package com.dddn.exception;

import com.dddn.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Result HandlerException(Exception e){
        log.error("程序出错了",e);
        return Result.error("程序出错了, 请联系后端开发人员~");
    }

    @ExceptionHandler
    public Result HandlerIllegalArgumentException(IllegalArgumentException e){
        log.error("程序出错了",e);
        String errorMessage = e.getMessage();
        return Result.error(errorMessage);
    }
}
