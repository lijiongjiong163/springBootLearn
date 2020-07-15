**适用于 06_SB_MybatisJTA；**
# Mybatis Plus + Atomikos
### 0.MybatisPlus使用
https://mybatis.plus/guide/
### 2.引入依赖
```xml
<!--        分布式事务管理 atomikos-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jta-atomikos</artifactId>
        </dependency>
<!--        mybatisplus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
```
### 3.配置双数据源
```yaml
#Atomikos双数据源
firstdb:
  uniqueResourceName: firstdbname
  xaDataSourceClassName: com.mysql.cj.jdbc.MysqlXADataSource
  xaProperties:
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&&useSSL=true&serverTimezone=UTC
    user: root
    password: 123456
  exclusiveConnectionMode: true
  minPoolSize: 3
  maxPoolSize: 10
  testQuery: select 1 from dual
seconddb:
  uniqueResourceName: seconddbname
  xaDataSourceClassName: com.mysql.cj.jdbc.MysqlXADataSource
  xaProperties:
    url: jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf8&&useSSL=true&serverTimezone=UTC
    user: root
    password: 123456
  exclusiveConnectionMode: true
  minPoolSize: 3
  maxPoolSize: 10
  testQuery: select 1 from dual
```
### 4.包结构
    由于是双数据源，那肯定是将两个数据源所处理的Mapper.xml还有mapper.java分开来，javaBean分开也行。
### 5.写配置类
由于你是要配置双数据源，那肯定要写两个配置类，这里用一个举例

当你是一个数据源的时候，这些都不用写，mybatis自动就帮你配置了，但是当你用双数据源的时候，就需要自己去写了，一共4步
- 1.配置dataSource
- 2.传入dataSource，生成SqlSessionFactory
- **3.传入dataSource，生成TransactionManager**
- 4.传入SqlSessionFactory，生成SqlSessionTemplate
- **5.将@MapperScan(basePackages = "com.ljj.mybatisjta.test1db",sqlSessionTemplateRef = "firstSqlSessionTemplate")放在配置类上，而不是启动Application上**
##### 说明：以上前4步是配置mybatis数据源的步骤，只要配置数据源就需要这4个组件，其中第3步是因为集成atomikos需要统一所以去掉了，第5步是因为是多数据源需要分开扫描包才加在配置类上的。当系统调用这个mapper时，就会使用扫描它的datasource去执行了
```java
package com.ljj.mybatisjta.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


import javax.sql.DataSource;
@Configuration
//todo  @MapperScan的sqlSessionFactoryRef是干啥的
@MapperScan(basePackages = "com.ljj.mybatisjta.test1db",sqlSessionFactoryRef = "firstSqlSessionFactory",sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class firstDataSourceConfig {
    //XA规范的数据源
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "firstdb")
    public DataSource firstXADataSource(){
    //这里用了Atomikos，所以要生成Atomikos数据源
        return new AtomikosDataSourceBean();
    }
    @Bean
    @Primary
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstXADataSource")DataSource dataSource) throws Exception {
        //todo  https://www.jb51.net/article/187628.htm
        //普通的mybatis用SqlSessionFactoryBean就行，但MybatisPlus要用MybatisSqlSessionFactoryBean
        //SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //因为我们偷懒用了mybatisPlus，所以不需要配置MapperLocations（mapper.xml放置的地方）
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test1db/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
//      由于Atomikos需要配置一个共同的TransactionManager来管理事务，所以将这里的TransactionManager去掉了
//    @Bean
//    @Primary
//    public firstDataSourceTransactionManager(@Qualifier("firstXADataSource")DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
    @Bean
    @Primary
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

```
### 6.配置Atomikos的TransactionManager
```java
package com.ljj.mybatisjta.config;


import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * TransactionManager配置类
 * 写法固定，不用看
 */
@Configuration
public class AtomikosTxManagerConfig {

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(1800000);
        return userTransactionImp;
    }

    @Bean(name = "txManager")
    public PlatformTransactionManager txManager() throws SystemException {
        UserTransaction userTransaction = userTransaction();
        TransactionManager transactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, transactionManager);
    }
}
```
**完事，这下在service中用@Transactional同时访问两个数据库就能进行事务管控了**