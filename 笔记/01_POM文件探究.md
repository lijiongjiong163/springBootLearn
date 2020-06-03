# POM.xml内容

首先打开POM.xml，发现里面有个父项目：spring-boot-starter-parent

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.0.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```


点进去看，发现这个spring-boot-starter-parent还依赖一个父项目：spring-boot-dependencies

### 1.spring Boot版本仲裁中心

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>2.3.0.RELEASE</version>
</parent>
```

由它（spring-boot-dependencies）来管理springBoot应用里面的所有依赖版本，也称为spring Boot的版本仲裁中心，有它以后我们以后导入依赖是不需要写版本号的（如果没有在仲裁中心里管理的依赖就要写版本号）

### 2.各种启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- web场景的场景启动器 -->
```

spring-boot-starter：spring-Boot场景启动器，帮我们导入了某个场景正常运行所需要的依赖包。

spring-boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来。要用什么功能就导入什么场景的启动器。