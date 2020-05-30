package com.ljj.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication来标注一个主程序类，说明这是一个springBoot应用
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //springBoot应用启动
        SpringApplication.run(Application.class, args);
    }

}
