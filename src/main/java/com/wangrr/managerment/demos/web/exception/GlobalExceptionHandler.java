package com.wangrr.managerment.demos.web.exception;

import com.wangrr.managerment.demos.web.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
*   全局异常处理器
* */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起，操作失误，请找管理员");
    }
}
