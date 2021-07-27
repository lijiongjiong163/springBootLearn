package com.ljj.redis_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisTemplateApplication.class, args);
    }

}
