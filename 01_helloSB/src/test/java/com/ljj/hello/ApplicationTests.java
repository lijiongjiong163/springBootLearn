package com.ljj.hello;

import com.ljj.hello.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    Person person;
    @Test
    void contextLoads() {

        System.out.println(person);
    }

}
