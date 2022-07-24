package test.com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2{

    @GetMapping(value="/hello2")
    public String hello(){
        return "hello2";
    }
}
