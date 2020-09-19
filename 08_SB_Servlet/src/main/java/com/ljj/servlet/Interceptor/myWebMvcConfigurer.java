package com.ljj.servlet.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration

public class myWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    MyInterceptor1 myInterceptor1;
    @Autowired
    MyInterceptor2 myInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 并写拦截规则
        registry.addInterceptor(myInterceptor1).addPathPatterns("/*");
        //多个拦截器时按照注册顺序去执行拦截器
        registry.addInterceptor(myInterceptor2).addPathPatterns("/users");
    }
}
