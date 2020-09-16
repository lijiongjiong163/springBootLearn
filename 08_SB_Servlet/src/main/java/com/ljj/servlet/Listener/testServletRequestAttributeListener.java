package com.ljj.servlet.Listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义RequestAttributeListener
 * 由于session和context的AttributeListener中的方法都和这个一样，所以不写了
 */
@Slf4j
@WebListener
public class testServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log.warn("--------------request添加属性");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        log.warn("--------------request删除属性");

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        log.warn("--------------request修改属性");
    }
}
