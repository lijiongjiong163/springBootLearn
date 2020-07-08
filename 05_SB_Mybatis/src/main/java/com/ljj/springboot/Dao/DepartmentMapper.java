package com.ljj.springboot.Dao;

import com.ljj.springboot.domain.Department;
import org.apache.ibatis.annotations.*;
//指定这是一个操作数据库的mapper,如果mapper多了会很麻烦，可以用@mapperScan标注在程序入口处，这样就能将指定包中的mapper都扫描到啦
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
