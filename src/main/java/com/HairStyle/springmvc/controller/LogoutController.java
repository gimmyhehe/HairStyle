package com.HairStyle.springmvc.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.IUserService;

/**
 * 
 * @author 
 *
 */
@Controller
public class LogoutController {

    @Resource
    private IUserService UserService;

    /**
     * 用户登录
     * 
     * @param 
     * @param 
     * @return
     */    
    @RequestMapping("/api/logout")
    @ResponseBody
    public Map<String, String> logout(HttpServletRequest req,HttpServletResponse response){
    	String user_name=req.getParameter("user_name");
        Cookie[] cookies = req.getCookies();
        Map<String, String> logoutstate = new HashMap<String, String>(); 
        for (Cookie cookie :cookies){//遍历所有Cookie
            if(cookie.getName().equals(user_name)){//找到对应的cookie
                cookie.setMaxAge(0);//Cookie并不能根本意义上删除，只需要这样设置为0
                cookie.setPath("/");//
                response.addCookie(cookie);//重新响应                
                logoutstate.put("status", "0");
        		logoutstate.put("msg", "成功注销");       		
            }
        }
		return logoutstate;
    }
}