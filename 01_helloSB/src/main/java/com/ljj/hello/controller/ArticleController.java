package com.ljj.hello.controller;

import com.ljj.hello.domain.AjaxResponse;
import com.ljj.hello.domain.Article;
import com.ljj.hello.domain.ExcelResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 后台传参的方法
 * @Slf4j  lombok标签，引入log对象,等于手写：private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
 * @RequestMapping 有4个变种
 * @PathVariable 从路径（指url）中获取参数
 * @RequestBody 从请求体中获取参数(可以接受嵌套数据)
 * @RequestParam 接收表单数据,或者是url中问号后的数据（不可接收嵌套数据）
 * @RequestHeader 接收请求头里的数据
 *
 * @ResponseBody 加上这个标签这个方法的返回值就会以json格式返回给前台，如果不加，就会使用返回值去view找相应的前端页面去了
 */

@RestController//效果等于类上加@Controller，然后每个方法上都加@ResponseBody(将返回值以json格式发送给前台 )

@Slf4j
@RequestMapping(value = "/rest")
public class ArticleController {
    @Autowired
    private AjaxResponse<Article> ajaxResponse;
    @Autowired
    private ExcelResponse<Article> excelResponse;

    // @RequestMapping(value = "/Articles/{id}",method = RequestMethod.GET)//这句话就等于@GetMapping
    /**
     * 查询用户
     * @param id  用户id
     * @return     用户信息
     */
    @GetMapping(value = "/Articles/{id}")//url上的参数叫路径变量，使用PathVariable获取(Path:路径  Variable：变量）
public AjaxResponse<Article> getArticle(@PathVariable("id") int id){
        Article article = Article.builder()
                .id(id)
                .author("曹雪芹")
                .content("红楼梦")
                .creatTime(new Date())
                .build();
        log.info("article:"+article);

        return ajaxResponse.success(article);

}
    //@RequestMapping(value = "/Articles/{id}",method = RequestMethod.POST)//这句话就等于@PostMapping
    /**
     * 新增用户
     * @param article  用户信息
     * @param aaa      请求头
     * @return  成功消息
     */
     @PostMapping(value = "/Articles")
     public AjaxResponse<Article> saveArticle(@RequestBody Article article,@RequestHeader String aaa){
        log.info("article:"+article+"aaa:"+aaa);
        return ajaxResponse.success();
    }

    /**
     * 修改用户
     * @param suibian 用户id
     * @param title  书名
     * @param content
     * @param author
     * @param creatTime
     * @return  成功消息
     */
    @PutMapping(value = "/Articles")
    public AjaxResponse<Article> updateArticle( @RequestParam("id")  int suibian,@RequestParam  String title,@RequestParam  String content,
                                       @RequestParam  String author,
                                       @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
                                           @RequestParam  Date creatTime
                                       //这两个标签可以同时使用，但是japidocs看到RequestBody就把这个请求当成json去生成文档了
                                   //    @RequestBody Article article
                                       ){

       log.info("article:"+suibian+"/"+title+"/"+content+"/"+author+"/"+creatTime);
        //if(article!=null)
       // log.info("article:"+article);
        return ajaxResponse.success();
    }
    @DeleteMapping(value = "/Articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable("id") int id){
        Article article = Article.builder()
                .id(id)
                .author("罗贯中")
                .content("三国")
                .creatTime(new Date())
                .build();
        log.info("article:"+article);
        return ajaxResponse.success(article);

    }
    // @RequestMapping(value = "/Articles/{id}",method = RequestMethod.GET)//这句话就等于@GetMapping
    @GetMapping(value = "/Articles/Excel/{id}")//url上的参数叫路径变量，使用PathVariable获取(Path:路径  Variable：变量）
    public ExcelResponse excelArticle(@PathVariable("id") int id){
        Article article = Article.builder()
                .id(id)
                .author("曹雪芹")
                .content("红楼梦")
                .creatTime(new Date())
                .build();
        log.info("article:"+article);
        return excelResponse.success(article);

    }
}
