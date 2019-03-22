package com.HairStyle.springmvc.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.User;



@Repository
public interface IUserDao {


	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */
	public User findUsersByUsernameDao(String user_name);
	public int findUsersByEmailDao(Map<String, String> map);
	public int findUsersByPhoneDao(Map<String, String> map);
	public User loginDao(Map<String, String> map);
	public boolean registerDao(Map<String, Object> map);
	public boolean modifyPasswordByUsernameDao(Map<String, String> map);
	public boolean modifyUser_dataByUserIDDao(Map<String, Object> map);
	public User get_user_dataDao(String user_name);
	public boolean upload_new_user_picDao(Map<String, Object> map);
	public boolean set_user_old_picDao(String user_name);

}
