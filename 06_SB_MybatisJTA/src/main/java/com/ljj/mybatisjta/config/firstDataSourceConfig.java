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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;


import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.ljj.mybatisjta.test1db",sqlSessionFactoryRef = "firstSqlSessionFactory",sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class firstDataSourceConfig {
    //XA规范的数据源
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "firstdb")
    public DataSource firstXADataSource(){
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
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test1db/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    @Primary
    public TransactionManager firstDataSourceTransactionManager(@Qualifier("firstXADataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    @Primary
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
