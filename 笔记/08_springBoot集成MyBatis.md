**适用于 05_SB_Mybatis；**

# 1.MyBatis的最佳实现

##### 1.使用MybatisPlus

只支持单表查询，单表操作数据库时使用

##### 2.使用xml方式

多表关联查询，动态sql

##### 3.使用注解方式

单表查询，适用于随便写两句简单的sql用



# 2. 整合MyBatis

## 2.1引入依赖

```xml
		<!--myBatis自己写的starter-->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.1</version>
		</dependency>
```

![](assets/搜狗截图20180305194443.png)

## 2.2注解版
#### 2.2.1 开启驼峰转换
在application.yml中加入
```yaml
#开启下划线驼峰转换（坑：只是说从数据库查出来的结果，下划线可以转成javabean中的驼峰格式，并不是说你写sql去数据库查时可以写成驼峰，错误示例见com.ljj.springboot.Dao.DepartmentMapper的insertDept方法）
mybatis:
  configuration:
    map-underscore-to-camel-case: true
```
#### 2.2.2 (可选)加@MapperScan
```java
@MapperScan("com.ljj.springboot.Dao")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```
#### 2.2.3直接写Dao就完事了（mapper）
```java
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
    //@Insert("insert into department(departmentName) values(#{departmentName})")   错误！写sql的时候还是要跟人家数据库字段名一致，这驼峰转换可管不了
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);
    //@Update("update department set department_name=#{departmentName} where id=#{id}")
    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
```
## 2.3配置版
#### 2.3.1在主配置文件中写扫描mapper.xml路径
```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
```
**注意：这个配置和@mapper（或@mapperScan）都需要，@mapper是负责将dao的接口标记成mapper，而配置文件中配的是扫描xml文件的配置**
#### 2.3.2用mybatis-generate生产代码就完事了
**注意：用generate生产的代码最好不要改，因为下次重新生成的代码就会覆盖掉你写的代码，所以建议将自己写的代码重新放在别的文件中**
## 2.4 mybatis plus
很方便，有机会再用

https://mp.baomidou.com/guide/
## 2.5 针对@mapper的解释
你会发现，Mapper层就是曾经的dao层，他两有什么区别呢：
- 1.dao他是有具体实现的呀，mapper是个接口
- 2.dao需要@Repository标签才能加入容器，而mapper不用，它用的是@mapper或@mapperScan

所以来解释以下这几个标签

- @Repository：和@Component一样，这些标签只能用在类上，接口就不能用
- @mapper：会自动给这个接口生成一个实现并加入容器中
- @MapperScan：指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类