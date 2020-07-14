package com.ljj.mybatisjta.Controller;


import com.ljj.mybatisjta.Service.articleService;
import com.ljj.mybatisjta.config.AjaxResponse;
import com.ljj.mybatisjta.domain.ArticleVO;
import com.ljj.mybatisjta.test1db.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public AjaxResponse addArticle(@RequestBody ArticleVO article){
        articleService.addArticle(article);
        return AjaxResponse.success("新建成功");
    }

    /**
     * 修改用户
     * @param article
     * @return
     */
    @PutMapping("/article")
    public AjaxResponse updateArticle(@RequestBody ArticleVO article){
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
