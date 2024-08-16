package com.example.study.demo.interceptor;

//import com.example.study.demo.context.BaseContext;

import com.example.study.demo.utils.JwtUtil;
import com.example.study.demo.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

    /**
     * JWT验证拦截器
     */
    @Component
    public class JwtTokenInterceptor implements HandlerInterceptor {
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            //令牌建议是放在请求头中，获取请求头中令牌
            String token = request.getHeader("token");
            try {
                Map<String, Object> map = JwtUtil.parseToken(token);
                ThreadLocalUtil.set(map);
                return true;//放行请求
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



