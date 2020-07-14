package com.ljj.mybatisjta.Service;

import com.ljj.mybatisjta.domain.ArticleVO;
import com.ljj.mybatisjta.test1db.Article;
import com.ljj.mybatisjta.test1db.ArticleDao;
import com.ljj.mybatisjta.test2db.Reader;
import com.ljj.mybatisjta.test2db.ReaderDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class articleService  {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ReaderDao readerDao;

    /**
     * @Transactional    数据库事务不能跨链接，也不能跨数据源，更不能跨库，像这种跨库的操作叫做分布式事务
     * @param article
     */
    @Transactional      //这玩意只能控制同一数据源的数据事务，如果同时操作两个数据库，那就有一个数据库没控制住
    public void addArticle(ArticleVO article){
        if(article.getId()!=0) {
            Article articlePO = new Article();
            Reader readerPO = new Reader();
            BeanUtils.copyProperties(article ,articlePO);
            BeanUtils.copyProperties(article.getReaders(),readerPO);
            articleDao.insert(articlePO);
            readerDao.insert(readerPO);
         //   int d = 1/0;
        }
            


            //int i =10/0;

    }
    public void updateArticle(ArticleVO article){
        if(article.getId()!=0){
            Article articlePO = new Article();
            BeanUtils.copyProperties(articlePO ,article);
            articleDao.updateById(articlePO);
        }

    }
    public void deleteArticle(int id){
        if(id!=0)
            articleDao.deleteById(id);
    }
    //查单条记录
    public Article findArticle(int id){
            return articleDao.selectById(id);

    }
    //查多条记录
    public List<Article> findAll(){
        return articleDao.selectList(null);
    }
}
