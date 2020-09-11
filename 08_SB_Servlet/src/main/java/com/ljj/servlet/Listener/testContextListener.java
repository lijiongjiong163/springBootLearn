package com.ljj.servlet.Listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义的ContextListener
 * @WebListener 用于将本对象实例化并加入到Ioc容器中去（需要配合@ServletComponentScan使用）
 */
@Slf4j
@WebListener
public class testContextListener implements ServletContextListener
{
    //Context对象初始化时的回调函数
    public void contextInitialized(ServletContextEvent sce) {
        log.info("++++++++++++++++++context对象创建监听");
    }
    //Context对象销毁时的回调函数
    public  void contextDestroyed(ServletContextEvent sce) {
        log.info("++++++++++++++++++context对象销毁监听");
    }
}
