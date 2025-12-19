package com.itheima.filter;

import com.itheima.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")

public class abcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //获取拦截请求路径
        String requestURI = request.getRequestURI();

        //如果是登录路径则放行
        if(requestURI.contains("/login")){
            log.info("放行前操作。。。");
            filterChain.doFilter(servletRequest,servletResponse);
            log.info("放行后操作。。。");
            return;
        }

        //如果不是则提取token
        String token = request.getHeader("Token");

        //如果token为空则报错401
        if(token==null || token.isEmpty()){
            log.error("token为空");
            response.setStatus(401);
            return;
        }

        //如果不为空则进行校验
        try{
            System.out.println(JwtUtil.parseJwt(token));
        }catch (Exception e){
            //出现异常报错401
            e.printStackTrace();
            log.error("token校验失败");
            response.setStatus(401);
            return;
        }

        log.info("token校验成功放行前操作。。。");

        //token校验成功则放行
        filterChain.doFilter(servletRequest,servletResponse);


        log.info("token校验成功放行后操作。。。");
    }

}
