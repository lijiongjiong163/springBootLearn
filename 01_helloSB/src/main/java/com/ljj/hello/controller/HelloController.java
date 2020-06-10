package com.ljj.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/SB")
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String Hello(String name){
        return "Hello,World!!!!!!!!!!"+name;
    }
}
