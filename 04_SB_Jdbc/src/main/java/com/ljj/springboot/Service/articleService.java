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

    /**
     * @Transactional    数据库事务不能跨链接，也不能跨数据源，更不能跨库，像这种跨库的操作叫做分布式事务
     * @param article
     */
    @Transactional      //这玩意只能控制同一数据源的数据事务，如果同时操作两个数据库，那就有一个数据库没控制住
    public void addArticle(Article article){
        if(article.getId()!=0)
            articleDao.addArticle(article,firstJdbcTemplate);
            articleDao.addArticle(article,secondJdbcTemplate);

            //int i =10/0;

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
