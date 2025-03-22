package com.dddn.interceptor;

import com.dddn.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取uri
        String uri = request.getRequestURI();
        //判断uri中是否有/login
        if(uri.contains("/login")){
            //有直接放行
            return true;
        }
        //获取请求头中的令牌
        String jwt = request.getHeader("token");
        //判断令牌是否存在 不存在就返回false
        if(jwt == null || jwt.isEmpty()){
            //返回401
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }
        //解析令牌 令牌错误则返回false
        try {
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }
        //放行
        return true;
    }
}
