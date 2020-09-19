package com.ljj.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 将三大组件加入spring容器的第二种方法，麻烦点，但是可以自定义执行顺序
 */
@Configuration
public class Registration {
    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        //Filter可以new，也可以靠依赖注入Bean
        registration.setFilter(new myFilter1());
        //起名
        registration.setName("myFilter1");
        //拦截的url
        registration.addUrlPatterns("/*");
        //排序（1先执行，然后2，3……）
        registration.setOrder(10);
        return registration;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        //Filter可以new，也可以靠依赖注入Bean
        registration.setFilter(new myFilter2());
        //起名
        registration.setName("myFilter2");
        //拦截的url
        registration.addUrlPatterns("/*");
        //排序小数先执行（1先执行，然后2，3……）
        registration.setOrder(1);
        return registration;
    }
}
