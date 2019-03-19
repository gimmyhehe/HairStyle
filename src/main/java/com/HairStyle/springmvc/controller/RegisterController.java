package com.HairStyle.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller

public class RegisterController {
	
	 @Resource
	    private UserServiceImpl UserService;

	    /**
	     * 用户注册
	     * 
	     * @param 
	     * @param 
	     * @return
	     */
	    @RequestMapping(value="/api/register",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, String> login(HttpServletRequest req) {
	    	String user_name=req.getParameter("user_name");
	    	String phone_area=req.getParameter("phone_area");
	    	String phone_number=req.getParameter("phone_number");
	    	String email=req.getParameter("email");
	    	Map<String, String> register_state = new HashMap<String, String>();
	    	if (UserService.isUserExist(user_name)) {
	    		register_state.put("msg", "用户名已存在！");
	    		register_state.put("status", "1");
	    	}
	    	else if (UserService.isEmailExist(email)) {
	    		register_state.put("msg", "该邮件已注册！");
	    		register_state.put("status", "2");

			}
	    	else if (UserService.isPhoneExist(phone_area,phone_number)) {
	    		register_state.put("msg", "该手机已注册！");
	    		register_state.put("status", "3");

			}
	    	else{
	    		
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String create_date = sdf1.format(date);
	    		String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	    		
	    		Map<String, String> map = new HashMap<String, String>();
	    		map.put("user_id", str+rannum);
	    		map.put("user_name", user_name);
	    		map.put("password", req.getParameter("password"));
	    		map.put("phone_area", phone_area);
	    		map.put("phone_number", phone_number);
	    		map.put("email", email);
	    		map.put("gender", req.getParameter("gender"));
	    		map.put("birth_date", req.getParameter("birth_date"));
	    		map.put("face_type", req.getParameter("face_type"));
	    		map.put("career", req.getParameter("career"));
	    		map.put("create_date", create_date);
	    		User user = UserService.register(map);
	    		register_state.put("msg", "注册成功！");
	    		register_state.put("status", "0");		
	    	}
	    	return register_state;
	    }




}
