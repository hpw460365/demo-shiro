package com.example.shiro.controller;

import com.example.shiro.bean.Result;
import com.example.shiro.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RestController
public class TestController{

    @GetMapping(value="/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping(value="/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        UsernamePasswordToken token = new UsernamePasswordToken("hepeiwen","123");
        subject.login(token);
        Session session2 = subject.getSession();
        System.out.println(subject.isPermittedAll());
        System.out.println(session2.getId());
        return "i am come in";
    }

    @GetMapping(value="/loginout")
    public String loginout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "i am get away";
    }


    @GetMapping(value="/hello")
    public String hello(){
        Subject subject = SecurityUtils.getSubject();
        return "hello";
    }

    @GetMapping(value="/register")
    public Result register(){
        Result result = new Result<User>();
        result.setCode("1");
        result.setMsg("成功");

        //List<User> users = new ArrayList<>();
        User users[] = new User[1];
        result.setData(users);
        User user = new User();
        user.setUsername("username1");
        user.setPassword("password2");
        users[0]=user;
        result.setData(user);
        return result;
    }

}
