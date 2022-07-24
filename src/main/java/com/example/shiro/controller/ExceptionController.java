package com.example.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class ExceptionController {


    @GetMapping(value="/allexception")
    public String register(){
        return "allexception";
    }


    @GetMapping(value="/personalexception")
    public String personal(){
        return "personalexception";
    }

}
