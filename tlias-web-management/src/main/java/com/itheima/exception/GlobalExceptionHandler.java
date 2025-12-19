package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result exceptionHandler(Exception e){
        log.error("程序出错了：",e);
        return Result.error("程序出错了，请联系管理员");
    }

    @ExceptionHandler
    public Result duplicateKeyExceptionHandler(DuplicateKeyException e){
        log.error("程序出错了：",e);
        String eString = e.toString();
        Integer i = eString.indexOf("Duplicate entry");
        String errorMsg = eString.substring(i).split(" ")[2];
        return Result.error(errorMsg+"手机号重复了");

    }
}
