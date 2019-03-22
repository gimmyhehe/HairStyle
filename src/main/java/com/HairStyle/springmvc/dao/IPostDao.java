package com.HairStyle.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.Poster;

public interface IPostDao {

	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */

	public boolean post_articleDao(Map<String, Object> map) ;
	public boolean insert_post_pic(Map<String, Object> map) ;
	
	public List<Poster> findPostByPost_typeDao(String post_type);
	public List<Poster> findPostByUser_nameDao(String user_name);

}
