package com.HairStyle.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //之前执行
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
      Cookie[] cookies = request.getCookies();//这里是取出Cookie
        if ((cookies!=null)){//判断Cookie是否为空
            for (Cookie cookie : cookies){//遍历Cookie判断有没有对应的name
                if (cookie.getName().equals("user_name")){//有就直接return true
                      return true;
                }
            }
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }

   
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
