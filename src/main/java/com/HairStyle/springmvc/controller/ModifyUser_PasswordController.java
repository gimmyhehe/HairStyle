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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "api")
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

		
	@RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyPassword(String user_name,String password,
			String new_password) {
		User user = new User();
        user.setUser_name(user_name);
        user.setPassword(password);
        user.setNew_Password(new_password);
		Map<String, Object> modifyPassword_state = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		if(password!=null&&new_password!=null){
			map2.put("user_name", user.getUser_name());
			map2.put("password",user.getNew_Password());
			map.put("user_name", user.getUser_name());
			map.put("password", user.getPassword());
			User user1 = UserService.login(map);
				if (user1 != null) {
					UserService.modifyPasswordByUsername(map2);
					modifyPassword_state.put("msg", "修改密码成功！");
					modifyPassword_state.put("status", 0);
	
				} else {
					modifyPassword_state.put("msg", "原密码错误！");
					modifyPassword_state.put("old", password);
					modifyPassword_state.put("status", 1);
				}
		}else{
			modifyPassword_state.put("msg", "原密码或新密码不能为空！");
			modifyPassword_state.put("status", 2);
			modifyPassword_state.put("old", password);
			modifyPassword_state.put("new", new_password);
		}
			return modifyPassword_state;
	}
	
	
	
	
	
	
}
