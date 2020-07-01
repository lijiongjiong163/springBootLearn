package com.ljj.springboot.tools;

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
 *
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
    @Primary
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
    @Bean
    public JdbcTemplate firstJdbcTemplate(@Qualifier("firstDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


}
