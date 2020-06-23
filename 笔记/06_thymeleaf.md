#thymeleaf
###1.引入

```xml
<properties>
        <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
        <thymeleaf-layout-dialect.version>2.1.1</thymeleaf-layout-dialect.version>
    </properties>
<!--        thymeleaf依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

```
###2.默认规则
```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

	public static final String DEFAULT_PREFIX = "classpath:/templates/";//默认前缀

	public static final String DEFAULT_SUFFIX = ".html";//默认后缀
}
```
只要把html文件放在classpath:/templates/路径下，就可以直接访问了
###3.语法

1）属性

​		th:text						修改标签体的内容

​		th:utext					  不转义特殊字符

​		th:任意html属性		修改这个属性

![](assets/2018-02-04_123955.png)
例如：
```html
<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
<!--只有th:if=后面的值为真的时候，这个标签才显示-->
```
###4.抽取公共代码(th:fragment)

```html
1、抽取公共片段
<div th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</div>

2、引入公共片段
<div th:insert="~{footer :: copy}"></div>
~{templatename::#selector}：模板名::选择器
~{templatename::fragmentname}:模板名::片段名
footer其实就是一个页面的名称
selector就是这个要公用标签的id，引用的时候记得加#
copy就是要这个要公用的代码段的th:fragment="copy"属性声明出来的
```

### 5.th:herf的相对路径和绝对路径

访问了一个路径叫：

```http
http://localhost:8080/emp/1002
```

确实到了controller，也确实返回到了响应的view，但结果页面样式乱了，f12报找不到样式文件

![image-20200623181933474](assets/image-20200623181933474.png)

居然是拼成了项目路径+emp+webjars....

后来发现是因为th:harf的相对路径和绝对路径问题，我的引入是这样写的

```html
<link th:href="@{webjars/bootstrap/4.5.0/dist/css/bootstrap.css}" rel="stylesheet">
```

地址前没加/，直接写地址的，这种写法就是相对路径的写法，以URL请求路径为根目录，webjars和1002同一级别，此时根路径为:

```http
http://localhost:8080/emp
```

拼接成

```http
http://localhost:8080/emp  +  webjars/bootstrap/4.5.0/dist/css/bootstrap.css
```

如果改成绝对路径：

```html
<link th:href="@{/webjars/bootstrap/4.5.0/dist/css/bootstrap.css}" rel="stylesheet">
```

就会以项目根目录去拼接：

```http
http://localhost:8080/webjars/bootstrap/4.5.0/dist/css/bootstrap.css
```



