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
		if (userDao.findUsersByUsernameDao(username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isEmailExist(Map<String, String> map) {
		if (userDao.findUsersByEmailDao(map) ==0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isPhoneExist(Map<String, String> map) {
		if (userDao.findUsersByPhoneDao(map) ==0) {
			return false;
		} else {
			return true;
		}
	}
	
	public User login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userDao.loginDao(map);
	}
	
	public boolean register(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return userDao.registerDao(map);
	}
	

	
	public boolean modifyPasswordByUsername(Map<String, String> map) {
		
		return userDao.modifyPasswordByUsernameDao(map);
	}
	
	public boolean modifyUser_dataByUserID(Map<String, Object> map) {
		 return userDao.modifyUser_dataByUserIDDao(map);
	}

	public User get_user_data(String user_name){
		
		return userDao.get_user_dataDao(user_name);
	}


	public boolean upload_new_user_pic(Map<String, Object> map){
		return userDao.upload_new_user_picDao(map);
	}
	
	public boolean set_user_old_pic(String uploader_id){
		return userDao.set_user_old_picDao(uploader_id);
	}


}
