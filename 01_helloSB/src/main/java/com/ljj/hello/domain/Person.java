package com.ljj.hello.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.Map;

/**
 * @ConfigurationProperties(prefix = "person")  从全局配置文件中取值，这个标签可以在类上，给类中的变量赋值，也可以给一个方法，就给该方法返回的对象中的的变量赋值
 * 这个类必须要加载到spring容器中
 * prefix中必须全小写字母，需要驼峰的话可以用_隔开
 * 这个类中的属性必须有set方法
 *
 *
 * @PropertySource  从指定文件中取值
 * @Validated jsr303校验
 *
 */
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource(value = "classpath:person.properties",encoding = "utf-8")
@Validated
public class Person {
    //@Value("${person.name}")
    private String name;
    @Min(21)
    private int age;
    private Map myMap;
    private String array;
    private dog myDog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map getMyMap() {
        return myMap;
    }

    public void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    public String getArray() {
        return array;
    }

    public void setArray(String array) {
        this.array = array;
    }

    public dog getMyDog() {
        return myDog;
    }

    public void setMyDog(dog myDog) {
        this.myDog = myDog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", myMap=" + myMap +
                ", array='" + array + '\'' +
                ", myDog=" + myDog +
                '}';
    }
}
class dog {

    private String dogName;
    private int dogAge;

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    @Override
    public String toString() {
        return "dog{" +
                "dogName='" + dogName + '\'' +
                ", dogAge=" + dogAge +
                '}';
    }

}
