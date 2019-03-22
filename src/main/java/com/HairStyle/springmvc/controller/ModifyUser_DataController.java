package com.HairStyle.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "api")
public class ModifyUser_DataController {
	
	@Resource
	private UserServiceImpl UserService;
	
	/**
     * 资料修改
     * 
     * @param 
     * @param 
     * @return
     */

	@RequestMapping(value="modifyUser_data",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyUser_data(User user) {
		String user_name=user.getUser_name();
		String phone_area=user.getPhone_area();
    	String phone_number=user.getPhone_number();
    	String email=user.getEmail();
    	String face_type=user.getFace_type();
    	String gender=user.getGender();
    	String career=user.getCareer();
    	Date birth_date=user.getBirth_date();
    	Map<String, Object> modifyUser_data_state = new HashMap<String,Object>();
    	Map<String, String> phone=new HashMap<String, String>();
    	Map<String, String> mail_map=new HashMap<String, String>();
    	phone.put("phone_area", phone_area);
    	phone.put("phone_number", phone_number);
    	phone.put("user_name", user_name);
    	mail_map.put("email", email);
    	
    	if(phone_area==null||phone_number==null||email==null||face_type==null||gender==null||career==null){
    		modifyUser_data_state.put("msg", "信息未完善！");
    		modifyUser_data_state.put("status", 4);
    	}
    	else if (UserService.isPhoneExist(phone)) {
			modifyUser_data_state.put("msg", "该手机已注册！");
			modifyUser_data_state.put("status", 2);
		}
    	else if (UserService.isEmailExist(mail_map)) {
    		modifyUser_data_state.put("msg", "该邮件已注册！");
    		modifyUser_data_state.put("status", 1);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_name",user_name);
			map.put("phone_area",phone_area);
			map.put("phone_number", phone_number);
			map.put("email", email);
			map.put("gender", gender);
			map.put("birth_date", birth_date);
			map.put("face_type", face_type);
			map.put("career", career);
			UserService.modifyUser_dataByUsername(map);
			modifyUser_data_state.put("msg", "修改资料成功！");
			modifyUser_data_state.put("status", 0);
			
	}
    	return modifyUser_data_state;
	}

}
