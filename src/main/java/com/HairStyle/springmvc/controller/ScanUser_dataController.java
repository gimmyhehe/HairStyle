package com.HairStyle.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

public class ScanUser_dataController {
	
	private UserServiceImpl UserService;
	
	/**
     * 查看用户资料
     * 
     * @param 
     * @param 
     * @return
     */
	@RequestMapping(value = "/api/userdata", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> initUser_data(HttpServletRequest req) {
		String user_name=req.getParameter("user_name");
		
		User user=UserService.get_user_data(user_name);
		
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_name", user_name);
			map.put("phone_area",user.getPhone_area());
			map.put("phone_number", user.getPhone_number());
			map.put("email", user.getEmail());
			map.put("gender", user.getGender());
			map.put("birth_date", user.getBirth_date());
			map.put("face_type", user.getFace_type());
			map.put("career", user.getCareer());

			
			return map;
	}
}
