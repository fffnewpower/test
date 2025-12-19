package com.itheima.configurer;


import com.itheima.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器 /**是匹配所有路径 /*是匹配所有一级路径，其他级路径不匹配
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
