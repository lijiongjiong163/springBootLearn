package com.ljj.springbootweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc   彻底接管springmvc，让自动配置失效
//@Component   从容器中去掉这个类，用工厂试一试
public class myMvcConfig implements WebMvcConfigurer {
    /**
     * 自定义视图映射，springBoot会遍历所有组件一起起作用
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }


}
//这个做法就是实现人家提供的xxxConfigurer类，然后加到容器中，springMVC就会连同这个视图映射器一起起作用，我们也可以做一个配置类专门去生成这些扩展对象，更符合spring的思想