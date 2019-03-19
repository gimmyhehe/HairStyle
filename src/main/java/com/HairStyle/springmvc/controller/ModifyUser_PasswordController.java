package com.HairStyle.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

public class ModifyUser_PasswordController {
	@Resource
    private UserServiceImpl UserService;

    /**
     * 密码修改
     * 
     * @param 
     * @param 
     * @return
     */

		
	@RequestMapping(value = "/api/modifyPassword", method = RequestMethod.POST)
	public Map<String, String> modifyPassword(@RequestParam("user_name") String user_name,
			@RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword, ModelMap model) {
		Map<String, String> modifyPassword_state = new HashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_name", user_name);
		map.put("password", password);
		User user = UserService.login(map);
			if (user != null) {
				UserService.modifyPasswordByUsername(user_name, newpassword);
				modifyPassword_state.put("msg", "修改密码成功！");

			} else {
				modifyPassword_state.put("msg", "原密码错误！");
			}

			return modifyPassword_state;
	}
	
	
	
	
	
	
}
