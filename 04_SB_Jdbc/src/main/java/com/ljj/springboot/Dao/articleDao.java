package com.ljj.springboot.Dao;

import com.ljj.springboot.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class articleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addArticle(Article article){
        jdbcTemplate.update("Insert into article values (?,?,?,?,?)",
                article.getId(),
                article.getAuthor(),
                article.getContent(),
                article.getTitle(),
                article.getCreatTime()
                );
    }
}
