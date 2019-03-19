package com.HairStyle.springmvc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.HairStyle.springmvc.dao.IUserDao;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.IUserService;


@Service
public class UserServiceImpl implements IUserService  {
	@Resource
	private IUserDao userDao;
	
	public boolean isUserExist(String username) {
		if (userDao.findUsersByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isEmailExist(String email) {
		if (userDao.findUsersByEmail(email) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isPhoneExist(String phone_area,String phone_number) {
		if (userDao.findUsersByPhone(phone_area,phone_number) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public User login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userDao.login(map);
	}
	
	public User register(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		return userDao.register(map);
	}
	

	
	public void modifyPasswordByUsername(String user_name, String newpassword) {
		
		userDao.modifyPasswordByUsername(user_name,newpassword);
	}
	
	public User modifyUser_dataByUsername(Map<String, String> map) {

		
		 return userDao.modifyUser_dataByUsername(map);
	}

	public User get_user_data(String user_name){
		
		return userDao.get_user_data(user_name);
	}

}
