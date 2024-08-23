package com.example.interceptors;

import com.example.pojo.Result;
import com.example.utils.JwtUtil;
import com.example.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor{
//拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     String  token=request.getHeader("Authorization");
        try {
            Map<String,Object> claims= JwtUtil.parseToken(token);
            ThreadLocalUtil TLU=new ThreadLocalUtil();
            TLU.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }


}
