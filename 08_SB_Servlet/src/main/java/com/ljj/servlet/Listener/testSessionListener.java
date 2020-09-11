package com.ljj.servlet.Listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 自定义的SessionListener
 * @WebListener 用于将本对象实例化并加入到Ioc容器中去（需要配合@ServletComponentScan使用）
 */
@Slf4j
@WebListener
public class testSessionListener implements HttpSessionListener
{
    //Session对象初始化时的回调函数
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("++++++++++++++++++Session对象创建监听");
    }
    //Session对象销毁时的回调函数
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("++++++++++++++++++Session对象销毁监听");
    }
}
