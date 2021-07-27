package com.ljj.redis_template.service;

import com.ljj.redis_template.domain.Employee;
import com.ljj.redis_template.mapper.empMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class empService {
@Autowired
private empMapper empMapper;
@Autowired
private RedisCacheManager cacheManager;

    /**
     * 方式一：使用注解的方式进行缓存
     * @param id
     * @return
     */
    @Cacheable("emp")
    public Employee getEmpById(int id){
     Employee emp= empMapper.getEmpById(id);
        System.out.println(emp);
        return  emp  ;
    }

    /**
     * 方式二：自己写代码进行缓存（二者效果一样）
     * @param id
     * @return
     */
    public Employee getEmpById2(int id){
        Employee emp= empMapper.getEmpById(id);
        System.out.println(emp);
        //1.用cacheManager获取cache
        Cache cache = cacheManager.getCache("emp");
        //2.用cache对象操作缓存
        cache.put(id,emp);
        return  emp  ;
    }
}
