**适用于 08_SB_Servlet**

## 一、Servlet

### Servlet的四个作用域对象（一个不常用）

1. ServletContext（应用级别，一整个应用就一个这个对象）
2. session（同一个用户使用同一个浏览器，在浏览器打开到关闭时公用一个）
3. page（页面级别，不常用）
4. request（一次请求）

![image-20200910100114624](assets/image-20200910100114624.png)

这些对象都可以通过上图中的方法去操作其属性：

![image-20200910100204302](assets/image-20200910100204302.png)
### Servlet三大组件
- Servlet（不多说）
- Listener
- Filter

## 二、Servlet Listener（监听器）

三个Servlet对象都有其相对应的监听器，可分为两种

#### 1.监听三大域对象的创建与销毁

- 监听Application对象：ServletContext Listener（用例：启动时可使用这个监听器往内存里加一些静态变量）
- 监听Session对象：HttpSession Listener（用例：统计在线人数）
- 监听Request对象：ServlertRequest Listener（用例：监听访问量）

#### 2.监听域对象内属性的变化

- HttpSessionAttributeListener
- ServletContextAttributeListener
- ServletRequestAttributeListener

#### 3.Listener使用方法

- 要用上面这些Listener，自己写个实现类，实现上面的接口，并重写人家的方法就行了，记得要注册。

#### 4.Listener注册方法

- 使用@WebListener注解（方便，推荐）
- ServletListenerFilterRegistrationBean（SpringBoot给Servlet三大组件都提供了相对应的RegistrationBean）
- web.xml中配置（老web项目）

## 三、过滤器

过滤器通常用于对访问Ip的识别呀，加密请求的解密呀，敏感词检测呀什么的。

- 在客户端发的请求访问后端资源之前，拦截这些请求
- 在服务器的响应发送回客户端之前，处理这些响应

![image-20200910174333010](assets/image-20200910174333010.png)

#### 1.详细说明：

![FireShot Capture 015 - Filter、FilterChain、FilterConfig 介绍 - 菜鸟教程 - www.runoob.com](assets/FireShot Capture 015 - Filter、FilterChain、FilterConfig 介绍 - 菜鸟教程 - www.runoob.com.png)

#### 2.具体使用例子如下：

​	写个Filter实现类就行了，记得要注册。

```java
package com.ljj.servlet.Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @WebFilter(filterName = "filter1",urlPatterns = "/*") 用于将本对象实例化并加入到Ioc容器中去（需要配合@ServletComponentScan使用）
 *                                                       这样子确实方便，但是无法保证过滤器链的执行顺序。
 */
@WebFilter(filterName = "filter1",urlPatterns = "/*")
public class myFilter1 implements Filter {
    /**
     *  初始化Filter时调用
     *  Web 容器创建 Filter 的实例对象后，将立即调用该 Filter 对象的 init 方法。init 方法在 Filter 生命周期中仅被执行一次。
     * @param filterConfig  包含 Filter 的配置和运行环境信息的 FilterConfig 对象
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter1 被加载了。。。。。。。。。。。。。");
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
            System.out.println("+++++++++++++++++++++Filter1执行前前前前++++++++++++++++++++++++++");
            filterChain.doFilter(servletRequest,servletResponse);//使用这个方法，如果有别的过滤器也要应用就会继续调用那个过滤器的doFilter方法，如果没有别的过滤器，就会执行目标 Servlet 的 service 方法
            servletResponse.setCharacterEncoding("UTF-8");
            System.out.println("+++++++++++++++++++++Filter1执行后后后后后++++++++++++++++++++++++++");

    }

    /**
     * 该方法在 Web 容器卸载 Filter 对象之前被调用，也仅执行一次。可以完成与 init 方法相反的功能，释放被该 Filter 对象打开的资源，例如：关闭数据库连接和 IO 流。
     */
    @Override
    public void destroy() {
        System.out.println("Filter1 被销毁了。。。。。。。。。。。。。");
    }
}
```

#### 3.filter注册spring方法：

- 利用@WebFilter注解（方便，但无法控制执行顺序）
- FilterRegistrationBean方式（推荐）
- XML配置方式（旧方式，spring用）