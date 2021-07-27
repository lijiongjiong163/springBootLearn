package com.ljj.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 学习js时写的测试类
 */
@Controller
public class testJSController {
    @GetMapping("/test/{id}")
    public String  test(@PathVariable Integer id, Model model){
        String.valueOf(id);
        System.out.println(id);
        String[] arr;
        arr=new String[4];
        arr[1]="哈哈";
       // arr[6]="嘿嘿";//超出范围赋值就报错了，而js则会阔容
        for (String s : arr) {
            System.out.println(s);
        }

        return "/testJS.html";
    }

    @GetMapping("/test2/{id}")
    public String  test2(@PathVariable Integer id, Model model) {
        return "/testJS2.html";

    }
}
