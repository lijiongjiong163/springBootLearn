package com.ljj.springbootweb.component;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义国际化功能
 */
public class MyLocaleResolver implements LocaleResolver
{
    /**
     * Resolve the current locale via the given request.
     * Can return a default locale as fallback in any case.
     * 通过给定的request解析出当前的区域。
     * 在任何情况下都可以可以返回一个默认的地区作为退路。
     * @param request the request to resolve the locale for
     * @return the current locale (never {@code null})
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
