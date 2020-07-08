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
    //将主键返回给对象
    @Options(useGeneratedKeys = true,keyProperty = "id")
    //@Insert("insert into department(departmentName) values(#{departmentName})")   错误！写sql的时候还是要跟人家数据库字段名一致，这驼峰转换可管不了
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);
    //@Update("update department set department_name=#{departmentName} where id=#{id}")
    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
