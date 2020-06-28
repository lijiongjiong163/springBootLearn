package com.ljj.springbootweb.config;

import com.ljj.springbootweb.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//整个配置类
@Configuration
public class myMvcConfigFactory {

    @Bean  //将返回值放入容器，key就是方法名~~
    public WebMvcConfigurer myMvcConfigurer(){
        return new WebMvcConfigurer() {
            /**
             * 给容器放入一个自定义视图映射器,映射url和html的关系
             *
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //也可以不写下面的loginHandlerInterceptor方法，直接在这new匿名对象就行了
                registry.addInterceptor(loginHandlerInterceptor())
                        //添加哪些请求会被拦截
                        .addPathPatterns("/**")
                        //添加哪些请求不会被拦截
                        .excludePathPatterns("/","/login","/**/*.css","/**/*.js","/asserts/**");
            }

        };
    }

    /**
     * 给容器放入一个自定义国际化
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    public HandlerInterceptor loginHandlerInterceptor(){
        return new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Object user = request.getSession().getAttribute("user");

                if (user==null){
                    //未登录
                    request.setAttribute("msg","请先登录！");
                    //getRequestDispatcher获取转发器,然后将request和response放进去
                    request.getRequestDispatcher("/").forward(request,response);

                    return false;
                }
                return true;

            }
        };
    }
}
