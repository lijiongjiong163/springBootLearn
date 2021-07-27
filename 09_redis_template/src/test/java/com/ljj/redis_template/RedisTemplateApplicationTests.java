package com.ljj.redis_template;

import com.ljj.redis_template.domain.Employee;
import com.ljj.redis_template.mapper.empMapper;
import com.ljj.redis_template.service.empService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisTemplateApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    empMapper empMapper;
    @Autowired
    empService empService;
    @Test
    void test1() {
        /*
        五种数据类型对应五个方法:opsFor****().
         */
//        stringRedisTemplate.opsForValue().append("k1","v1");
//        String k1 = stringRedisTemplate.opsForValue().get("k1");
//        System.out.println(k1);

        //操作list
        stringRedisTemplate.opsForList().leftPush("mylist","张三");
        stringRedisTemplate.opsForList().leftPush("mylist","李四");
        String mylist1 = stringRedisTemplate.opsForList().rightPop("mylist");

    }
    @Test
    public void test2(){
        Employee emp = empMapper.getEmpById(1);
        System.out.println(emp);
        //将这个对象存入redis时需要把对象序列化，所以这个对象必须实现Serializable。
        redisTemplate.opsForValue().set("emp-01",emp);
        //默认使用jdk自带的序列化器进行序列化的，所以在redis中看不懂，但是取出来照样能用。如需按照json格式序列化，那就自己写个redisTemplate，把序列化器换成json的即可
        Employee emp01 = (Employee)redisTemplate.opsForValue().get("emp-01");
        System.out.println(emp01);
    }
}
