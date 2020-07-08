package com.ljj.springboot.tools;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.ljj.springboot.domain.Reader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Primary 如果容器有多个同类型的bean，autowired会优先选择带@Primary标签的bean
 * @ConfigurationProperties(prefix = "spring.datasource.one")两种用法：
 *      1.用再类上，直接给成员变量赋值
 *      2.用在方法上，给方法的返回值赋值
 *
 * spring知识回顾：
 *      当使用注解配置的方式时（@Configuration），如果这个方法有参数，spring容器会自动去容器中找有没有可用的这个形参的bean对象。
 *      查找的方式和Autowired一样：
 *          自动按照类型注入，没有同类型报错；有唯一的则成功；有多个则用被它修饰的变量名去匹配bean的名称，有则成功，没有报错
 *
 *       @Qualifier  1.用在成员变量上时，必须和@Autowired一起用，在@Autowired找到的范围中（同类型），再用@Qualifier（“beanName”）去匹配，
 *                   这种情况下，@Qualifier+@Autowired=@Resource;
 *                   2.用在形参上时，那就是在@Configuration中用呀，人家自动按@Autowired方式给你找，所以看似单独用，其实还是一起用的效果啦
 *
 *
 */
@Configuration
public class DataSourceConfig {
    //普通的双数据源
    //@Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource firstDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().build();
    }

    //XA规范的双数据源
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "firstdb")
    public DataSource firstXADataSource(){
        return new AtomikosDataSourceBean();
    }
    @Bean
    @ConfigurationProperties(prefix = "seconddb")
    public DataSource secondXADataSource(){
        return new AtomikosDataSourceBean();
    }
    @Bean
    public JdbcTemplate firstJdbcTemplate(@Qualifier("firstXADataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondXADataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


}
