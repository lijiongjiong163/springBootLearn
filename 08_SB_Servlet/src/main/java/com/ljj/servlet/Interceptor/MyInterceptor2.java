package com.ljj.servlet.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("MyInterceptor2的preHandle执行啦（请求前调用）！！！！！！！！！！！！！！！！");
        //返回false则请求中断
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.warn("MyInterceptor2的postHandle执行啦（请求后调用）！！！！！！！！！！！！！！！！");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.warn("MyInterceptor2的afterCompletion执行啦（请求调用完成后回调方法，即在视图渲染完成后回调）！！！！！！！！！！！！！！！！");

    }
}
