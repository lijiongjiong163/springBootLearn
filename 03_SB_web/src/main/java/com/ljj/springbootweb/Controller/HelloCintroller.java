package com.ljj.springbootweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloCintroller {
    @RequestMapping("success")
    public String success(Map<String,Object> map){
        map.put("name","<h1>小明</h1>");
        map.put("users", Arrays.asList("张三","李四","王五"));
        return "success";
    }
}
