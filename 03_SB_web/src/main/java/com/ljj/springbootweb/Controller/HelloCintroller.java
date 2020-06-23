package com.ljj.springbootweb.Controller;

import com.ljj.springbootweb.dao.DepartmentDao;
import com.ljj.springbootweb.dao.EmployeeDao;
import com.ljj.springbootweb.entities.Department;
import com.ljj.springbootweb.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class HelloCintroller {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

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
    @GetMapping("emps")
    public String getAllEmps(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @GetMapping("emp")
    public String addEmpPage(Model model){
        model.addAttribute("dept",departmentDao.getDepartments());
        return "emp/add";
    }
    @PostMapping("emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        logger.info(employee.toString());
        //直接return到list页面的话会没有任何数据的，不行，所以要return
        //return "emp/list";
        //springmvc会拿方法返回值直接找view，这样写直接去找emps.html了
        //return "emps";
        //redirect:重定向      /代表当前项目路径
        //forward：转发
        return "redirect:/emps";
    }
    @GetMapping("/emp/{id}")
    public String updateEmpPage(@PathVariable("id") Integer id ,Model model){
        model.addAttribute("dept",departmentDao.getDepartments());
        model.addAttribute("emp",employeeDao.get(id));
        return "emp/add";
    }
}
