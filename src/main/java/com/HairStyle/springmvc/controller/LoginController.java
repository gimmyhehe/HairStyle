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

public class LoginController {

    @Resource
    private IUserService UserService;

    /**
     * 用户登录
     * 
     * @param 
     * @param 
     * @return
     */
    @RequestMapping("/api/login")
    @ResponseBody
    public Map<String, String> login(HttpServletRequest req, HttpServletResponse response) {
    	String user_name=req.getParameter("user_name");
    	String password=req.getParameter("password");
    	Map<String, String> loginstate = new HashMap<String, String>(); 
    	if (user_name!=null||password!=null){
    		Map<String, String> map = new HashMap<String, String>();   		
    		  
    		map.put("user_name", user_name);
    		map.put("password", password);
    		User user = UserService.login(map);
    		if (user != null) {//登录成功进入首页
    			Cookie cookie1 = new Cookie("user_name",user_name);
    			cookie1.setMaxAge(60*60);//这里设置设置有效时间，单位的秒，我这里是一小时
                cookie1.setPath("/HairStyle");//这里是之根目录下所有的目录都可以共享Cookie
                response.addCookie(cookie1);//添加Cookie
                loginstate.put("status", "0");
                loginstate.put("msg", "登陆成功！");
    			
    		} else {//登录失败回到登录页面
    			loginstate.put("status", "1");
                loginstate.put("msg", "用户名或密码输入错误,请重新输入！");
                
    		}
    	}else{
    		loginstate.put("status", "3");
    		loginstate.put("msg", "用户名或密码不为空。");

    	}
    	return loginstate;
    }
}