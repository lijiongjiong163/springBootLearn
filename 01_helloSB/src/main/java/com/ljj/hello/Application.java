package com.ljj.hello;

import com.ljj.hello.domain.Article;
import com.ljj.hello.domain.Person;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @SpringBootApplication来标注一个主程序类，说明这是一个springBoot应用
 * 这个标签相当于：
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 * 三个配置
 */
@SpringBootApplication
public class Application {



    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\Idea_workspace\\springBootLearn\\01_helloSB"); // 项目根目录
        config.setProjectName("01_helloSB"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("D:\\Idea_workspace\\springBootLearn\\01_helloSB\\apidocs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        config.addPlugin(new MarkdownDocPlugin());
        Docs.buildHtmlDocs(config); // 执行生成文档
        //springBoot应用启动
        Class<?>[] arr = {Application.class};
        ConfigurableApplicationContext run =SpringApplication.run(arr, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(run.getBean("person",Person.class));
        System.out.println(run.getBean("article1", Article.class));

    }

}
