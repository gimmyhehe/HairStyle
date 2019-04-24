package com.HairStyle.springmvc.service;

import java.util.List;
import java.util.Map;

import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.model.Reply;
import com.HairStyle.springmvc.model.Collection;
import com.HairStyle.springmvc.model.Common;
import com.HairStyle.springmvc.model.Post_Type;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.like_table;
/**
 * 
 * 
 * @param map
 * @return
 */

public interface IPostService {
	
	public boolean Post_article(Map<String, Object> map);
	public boolean Update_article(Map<String, Object> map);
	public boolean insert_post_picture(Map<String, Object> map);
	public boolean delete_post_picture(Map<String, Object> map);

	public boolean Delete_article(Map<String, Object> map);
	public Post_Type getPostContentByType(Map<String, Object> map);
	public User getPostContentByUser_id(Map<String, Object> map);
	public Poster getPostByPost_id(Map<String, Object> map);
	
	public boolean makecommon(Common common);
	public boolean deletecommon(String common_id);
	public boolean makereply(Reply reply);
	public boolean deletereply(String reply_id);
	public boolean search_post_picture(Map<String, Object> mapforcancelpic);
	public boolean makelike(like_table lt); 
	public boolean deletelike(String like_id);
	public boolean makecollect(Collection ct);
	public boolean deletecollect(String collect_id);
	
	public User getuser_likeByUser_id(Map<String, Object> map);
	public User getuser_collectByUser_id(Map<String, Object> map);
}
