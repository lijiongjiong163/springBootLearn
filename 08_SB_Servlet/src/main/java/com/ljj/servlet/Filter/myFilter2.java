package com.ljj.servlet.Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @WebFilter(filterName = "filter1",urlPatterns = "/*") 用于将本对象实例化并加入到Ioc容器中去（需要配合@ServletComponentScan使用）
 *                                                       这样子确实方便，但是无法保证过滤器链的执行顺序。
 */
//@WebFilter(filterName = "filter1",urlPatterns = "/*")
public class myFilter2 implements Filter {
    /**
     *  初始化Filter时调用
     *  Web 容器创建 Filter 的实例对象后，将立即调用该 Filter 对象的 init 方法。init 方法在 Filter 生命周期中仅被执行一次。
     * @param filterConfig  包含 Filter 的配置和运行环境信息的 FilterConfig 对象
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter2 被加载了...........................................");
        System.out.println(filterConfig);
    }

    /**
     *
     * 当一个 Filter 对象能够拦截访问请求时，Servlet 容器将调用 Filter 对象的 doFilter 方法。
     * @param servletRequest 就是此次客户端发过来的请求
     * @param servletResponse 服务端返回去的响应
     * @param filterChain 过滤器链对象
     * @throws IOException
     * @throws ServletException
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            servletRequest.setCharacterEncoding("UTF-8");
            System.out.println("+++++++++++++++++++++Filter2执行前前前前++++++++++++++++++++++++++");
            filterChain.doFilter(servletRequest,servletResponse);//使用这个方法，如果有别的过滤器也要应用就会继续调用那个过滤器的doFilter方法，如果没有别的过滤器，就会执行目标 Servlet 的 service 方法
            servletResponse.setCharacterEncoding("UTF-8");
            System.out.println("+++++++++++++++++++++Filter2执行后后后后后++++++++++++++++++++++++++");

    }

    /**
     * 该方法在 Web 容器卸载 Filter 对象之前被调用，也仅执行一次。可以完成与 init 方法相反的功能，释放被该 Filter 对象打开的资源，例如：关闭数据库连接和 IO 流。
     */
    @Override
    public void destroy() {
        System.out.println("Filter2 被销毁了..........................................");
    }
}
