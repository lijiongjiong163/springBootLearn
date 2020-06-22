package com.ljj.springbootweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    /**
     *  登录函数
     * @param username
     * @param password
     * @param map  错误信息放在map中返回给前台
     * @param httpSession   获取session对象，将登录信息传入，保证登入的校验
     * @return
     */
    @PostMapping("login")
    public String login(@RequestParam("username")String username, @RequestParam String password , Map<String,Object> map, HttpSession httpSession){
        if (username!=null&&"123456".equals(password)){
            //登陆成功
            httpSession.setAttribute("user",username);//将用户信息存入session，然后每次都用拦截器去校验
            return "redirect:/main.html";
        }
        //登录失败，将错误信息放在map中返回给前台
        map.put("msg","用户名或密码错误！");
        return "login";
    }
}
