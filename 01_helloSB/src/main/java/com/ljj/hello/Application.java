package com.ljj.hello;

import com.ljj.hello.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication来标注一个主程序类，说明这是一个springBoot应用
 * 这个标签相当于：
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 * 三个配置
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //springBoot应用启动
        SpringApplication.run(Application.class, args);
    }

}
