package com.ljj.servlet.Listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义的RequestListener
 * @WebListener 用于将本对象实例化并加入到Ioc容器中去（需要配合@ServletComponentScan使用）
 */
@Slf4j
@WebListener
public class testRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("++++++++++++++++++Request对象销毁监听");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("++++++++++++++++++Request对象初始化监听");
    }
}
