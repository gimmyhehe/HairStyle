package com.HairStyle.springmvc.service;

import java.util.Map;

import com.HairStyle.springmvc.model.User;

public interface IUserService {


	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */
	public User login(Map<String, String> map);
	public boolean register(Map<String, Object> map);
	public boolean isUserExist(String username);
	public boolean isEmailExist(Map<String, String> map);
	public boolean isPhoneExist(Map<String, String> map);
	public boolean modifyPasswordByUsername(Map<String, String> map); 
	public boolean modifyUser_dataByUsername(Map<String, Object> map);
	public User get_user_data(String user_name);

}