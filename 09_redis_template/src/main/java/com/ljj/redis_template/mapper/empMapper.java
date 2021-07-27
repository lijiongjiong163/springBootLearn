package com.ljj.redis_template.mapper;

import com.ljj.redis_template.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface empMapper {
    @Select("select * from employee where id=${id}")
    public Employee getEmpById(int id);
}
