package com.HairStyle.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;


@RequestMapping(value = "/api/modifyUser_data")
public class ModifyUser_DataController {
	private UserServiceImpl UserService;
	
	/**
     * 资料修改
     * 
     * @param 
     * @param 
     * @return
     */
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, String> initUser_data(HttpServletRequest req) {
		String user_name=req.getParameter("user_name");
		
		User user=UserService.get_user_data(user_name);
		
			Map<String, String> map = new HashMap<String, String>();

			map.put("phone_area",user.getPhone_area());
			map.put("phone_number", user.getPhone_number());
			map.put("email", user.getEmail());
			map.put("gender", user.getGender());
			map.put("birth_date", user.getBirth_date());
			map.put("face_type", user.getFace_type());
			map.put("career", user.getCareer());
			
			return map;
	}
	

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> modifyUser_data(HttpServletRequest req) {
		String user_name=req.getParameter("user_name");
		String phone_area=req.getParameter("phone_area");
    	String phone_number=req.getParameter("phone_number");
    	String email=req.getParameter("email");
    	Map<String, String> modifyUser_data_state = new HashMap<String, String>();
    	if (UserService.isEmailExist(email)) {
    		modifyUser_data_state.put("msg", "该邮件已注册！");
    		modifyUser_data_state.put("status", "1");
		}
    	else if (UserService.isPhoneExist(phone_area,phone_number)) {
			modifyUser_data_state.put("msg", "该手机已注册！");
			modifyUser_data_state.put("status", "2");
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_name",user_name);
			map.put("phone_area",phone_area);
			map.put("phone_number", phone_number);
			map.put("email", email);
			map.put("gender", req.getParameter("gender"));
			map.put("birth_date", req.getParameter("birth_date"));
			map.put("face_type", req.getParameter("face_type"));
			map.put("career", req.getParameter("career"));
			UserService.modifyUser_dataByUsername(map);
			modifyUser_data_state.put("msg", "修改资料成功！");
			modifyUser_data_state.put("status", "0");
			
	}
    	return modifyUser_data_state;
	}

}
