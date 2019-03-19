package com.HairStyle.springmvc.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.User;



@Repository
public interface IUserDao {


	/**
	 * 登录
	 * 
	 * @param map
	 * @return
	 */
	public User findUsersByUsername(String user_name);
	public User findUsersByEmail(String email);
	public User findUsersByPhone(String phone_area,String phone_number);
	public User login(Map<String, String> map);
	public User register(Map<String, String> map);
	public int modifyPasswordByUsername(String user_name,String newpassword);
	public User modifyUser_dataByUsername(Map<String, String> map);
	public User get_user_data(String user_name);
}
