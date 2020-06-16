package com.ljj.springbootweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloCintroller {
    @RequestMapping("success")
    public String success(Map<String,Object> map){
        map.put("name","小明");
        return "success";
    }
}
