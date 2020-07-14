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


import javax.sql.DataSource;
@Configuration
@MapperScan(basePackages = "com.ljj.mybatisjta.test2db",sqlSessionFactoryRef = "secondSqlSessionFactory")
public class secondDataSourceConfig {



        //XA规范的数据源

        @Bean
        @ConfigurationProperties(prefix = "seconddb")
        public DataSource secondXADataSource(){
            return new AtomikosDataSourceBean();
        }
        @Bean
        public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondXADataSource")DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
          //  sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test2db/*.xml"));
            return sqlSessionFactoryBean.getObject();
        }
//        @Bean
//        public DataSourceTransactionManager secondDataSourceTransactionManager(@Qualifier("secondXADataSource")DataSource dataSource){
//            return new DataSourceTransactionManager(dataSource);
//        }
        @Bean
        public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }


