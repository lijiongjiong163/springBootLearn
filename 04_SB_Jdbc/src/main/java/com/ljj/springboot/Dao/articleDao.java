package com.ljj.springboot.Dao;

import com.ljj.springboot.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class articleDao {
    @Resource
    private JdbcTemplate firstJdbcTemplate;

    //增删改都是用jdbcTemplate.update方法
    public void addArticle(Article article,JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null)
            jdbcTemplate=firstJdbcTemplate;
            int i =jdbcTemplate.update("Insert into article values (?,?,?,?,?)",
                article.getId(),
                article.getAuthor(),
                article.getContent(),
                article.getTitle(),
                article.getCreateTime()
                );
        System.out.println("共有"+i+"行被操作");
    }
    public void updateArticle(Article article,JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null)
            jdbcTemplate=firstJdbcTemplate;
        int i =jdbcTemplate.update("update  article set author=? , content=?,title=?,create_time=? where id = ? ",
                article.getAuthor(),
                article.getContent(),
                article.getTitle(),
                article.getCreateTime(),
                article.getId()
        );
        System.out.println("共有"+i+"行被操作");
    }
    public void deleteArticle(int id,JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null)
            jdbcTemplate=firstJdbcTemplate;
        int i =jdbcTemplate.update("delete from article  where id = ? ",id
        );
        System.out.println("共有"+i+"行被操作");
    }
    //查单条记录
    public Article findArticle(int id,JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null)
            jdbcTemplate=firstJdbcTemplate;
        return jdbcTemplate.queryForObject("select * from Article where id=?", new Object[]{id}, new BeanPropertyRowMapper<Article>());

    }
    //查多条记录
    public List<Article> findAll(JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null)
            jdbcTemplate=firstJdbcTemplate;
        return jdbcTemplate.query("select * from article",new BeanPropertyRowMapper<>(Article.class));
    }

}
