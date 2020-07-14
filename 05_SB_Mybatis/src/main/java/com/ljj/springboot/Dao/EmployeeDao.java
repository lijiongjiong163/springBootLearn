package com.ljj.springboot.Dao;

import com.ljj.springboot.domain.Employee;
import com.ljj.springboot.domain.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * generator自动生成的
 */
public interface EmployeeDao {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}