package com.example.exception;

import com.example.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
   public Result handlerException(Exception e) {
       e.printStackTrace();
       return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败，请检查原因" );
   }

}
