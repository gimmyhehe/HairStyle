package com.HairStyle.springmvc.service;

import java.util.List;
import java.util.Map;

import com.HairStyle.springmvc.model.Poster;

/**
 * 
 * 
 * @param map
 * @return
 */

public interface IPostService {
	
	public boolean Post_article(Map<String, Object> map);
	public boolean insert_post_picture(Map<String, Object> map);
	
	public List<Poster> find_post_picture(String str);
}
