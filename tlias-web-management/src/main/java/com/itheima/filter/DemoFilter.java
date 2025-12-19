package com.itheima.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
//@Order(2)
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 拦截器初始化。。。");
    }

    //拦截到请求之后执行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter 拦截到请求。。。");
        //拦截到请求后放行
        filterChain.doFilter(servletRequest,servletResponse);

        log.info("doFilter 放行后。。。");
    }

    @Override
    public void destroy() {
        log.info("destroy 拦截器销毁。。。");
    }
}
