package com.ljj.springboot.Controller;

import com.ljj.springboot.Dao.EmployeeDao;
import com.ljj.springboot.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    @Autowired
    EmployeeDao employeeDao;
    @GetMapping("emp/{id}")
    public Employee get(@PathVariable("id") int id){
        return employeeDao.selectByPrimaryKey(id);
    }
    @GetMapping("/emp")
    public Employee save(Employee employee){
        //insert方法就是插入，空的值就插入null；insertSelective方法是javabean有啥属性不为空就插入啥，属性为空的就不写
        employeeDao.insertSelective(employee);
        return employee;
    }
}
