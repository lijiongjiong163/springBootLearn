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



