package com.ljj.springboot.Service;

import com.ljj.springboot.Dao.articleDao;
import com.ljj.springboot.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class articleService {
    @Autowired
    private articleDao articleDao;
    @Resource
    private JdbcTemplate firstJdbcTemplate;
    @Resource
    private JdbcTemplate secondJdbcTemplate;

    @Transactional
    public void addArticle(Article article){
        if(article.getId()!=0)
            articleDao.addArticle(article,secondJdbcTemplate);
            articleDao.addArticle(article,firstJdbcTemplate);


    }
    public void updateArticle(Article article){
        if(article.getId()!=0)
       articleDao.updateArticle(article,firstJdbcTemplate);
    }
    public void deleteArticle(int id){
        if(id!=0)
       articleDao.deleteArticle(id,firstJdbcTemplate);
    }
    //查单条记录
    public Article findArticle(int id){
            return articleDao.findArticle(id,firstJdbcTemplate);

    }
    //查多条记录
    public List<Article> findAll(){
        return articleDao.findAll(firstJdbcTemplate);
    }
}
