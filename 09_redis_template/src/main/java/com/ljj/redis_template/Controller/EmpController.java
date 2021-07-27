package com.ljj.redis_template.Controller;

import com.ljj.redis_template.domain.Employee;
import com.ljj.redis_template.service.empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.GetAppletMessage;

@RestController
public class EmpController {
    @Autowired
    empService empService;

    @GetMapping("emp/{id}")
    public Employee getEmpById(@PathVariable int id){
            return empService.getEmpById(id);
    }

}
