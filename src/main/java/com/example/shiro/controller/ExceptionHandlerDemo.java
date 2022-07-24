package com.example.shiro.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerDemo {
    /**
     * 处理所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String allException(){
        return "allexception";
    }


    /**
     * 处理部分明确异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String personalException(){
        return "personalexception";
    }
}
