package com.ljj.hello.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration(proxyBeanMethods = true)
public class Test {
    @Bean("article1")
    public Article article(){
        return new Article(1,"java基础","content","nobody",new Date(),null);
    }
}
