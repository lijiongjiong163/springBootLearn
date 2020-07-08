package com.ljj.springboot.Controller;

import com.ljj.springboot.Dao.DepartmentMapper;
import com.ljj.springboot.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    DepartmentMapper departmentMapper;
    @GetMapping("dept/{id}")
    public Department get(@PathVariable("id") int id){
        return departmentMapper.getDeptById(id);
    }
    @GetMapping("/dept")
    public Department save(Department department){

        departmentMapper.insertDept(department);
        return department;
    }

}
