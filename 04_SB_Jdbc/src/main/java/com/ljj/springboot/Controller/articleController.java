package com.ljj.springboot.Controller;

import com.ljj.springboot.Service.articleService;
import com.ljj.springboot.domain.Article;
import com.ljj.springboot.tools.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class articleController {
    @Autowired
    private articleService articleService;

    /**
     * 新增用户
     * @param article
     * @return
     */
    @PostMapping("/article")
    public AjaxResponse addArticle(@RequestBody Article article){
        articleService.addArticle(article);
        return AjaxResponse.success("新建成功");
    }

    /**
     * 修改用户
     * @param article
     * @return
     */
    @PutMapping("/article")
    public AjaxResponse updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return AjaxResponse.success("修改成功");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/article/{id}")
    public AjaxResponse deleteArticle(@PathVariable int id){
        articleService.deleteArticle(id);
        return AjaxResponse.success("删除成功");
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @GetMapping("/article/{id}")
    public AjaxResponse findArticle(@PathVariable int id){

        return AjaxResponse.success( articleService.findArticle(id));
    }

    /**
     * 查询所有用户
     * @return
     */
    //查多条记录
    @GetMapping("/articles")
    public AjaxResponse findAll(){
        return AjaxResponse.success(articleService.findAll());
    }
}
