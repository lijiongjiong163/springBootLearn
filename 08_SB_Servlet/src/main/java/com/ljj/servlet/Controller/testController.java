package com.ljj.servlet.Controller;


import com.ljj.servlet.domain.testUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class testController {
    @GetMapping("/users")
    public ArrayList<testUser> getUsers(HttpServletRequest request, HttpSession session){
        request.setAttribute("a","a");
        request.setAttribute("a","b");
        request.getAttribute("a");
        request.removeAttribute("a");

        session.setAttribute("a","a");
        session.setAttribute("a","b");
        session.getAttribute("a");
        session.invalidate();
        return getUser();
    }

    /**
     * 不用关注
     * @return
     */
    public ArrayList<testUser> getUser(){
        ArrayList<testUser> testUsers = new ArrayList<>();
        testUsers.add(new testUser(1,"杀掉",12));
        testUsers.add(new testUser(2,"sb",13));
        testUsers.add(new testUser(3,"傻逼",14));
        log.info("返回所有用户信息");



        return testUsers;
    }
}
