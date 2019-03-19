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
	public User register(Map<String, String> map);
	public boolean isUserExist(String username);
	public boolean isEmailExist(String email);
	public boolean isPhoneExist(String phone_area,String phone_number);
	public void modifyPasswordByUsername(String username, String newpassword); 
	public User modifyUser_dataByUsername(Map<String, String> map);
	public User get_user_data(String user_name);
}