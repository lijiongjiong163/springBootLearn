package com.ljj.springboot.Dao;

import com.ljj.springboot.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class articleDaoTest {
    @Autowired
    private articleDao articleDao;
    @Test
    void addArticle() {
        /*articleDao.addArticle(new Article().builder()
                .author("罗贯中")
                .id(1)
                .title("三国")
                .content("content")
                .createTime(new Date())
                .readers(Collections.emptyList())
                .build());*/

    }
}