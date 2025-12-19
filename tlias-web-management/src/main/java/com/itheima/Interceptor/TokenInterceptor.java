package com.itheima.Interceptor;

import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Interceptor拦截器启动");

        //获取拦截请求路径
        String requestURI = request.getRequestURI();

        //如果不是则提取token
        String token = request.getHeader("Token");

        //如果token为空则报错401
        if(token==null || token.isEmpty()){
            log.error("token为空");
            response.setStatus(401);
            return false;
        }

        //如果不为空则进行校验
        try{
            JwtUtil.parseJwt(token);
        }catch (Exception e){
            //出现异常报错401
            e.printStackTrace();
            log.error("token校验失败");
            response.setStatus(401);
            return false;
        }


        return true;
    }
}
