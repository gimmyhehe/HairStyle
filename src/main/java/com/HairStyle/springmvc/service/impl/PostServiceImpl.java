package com.HairStyle.springmvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.HairStyle.springmvc.dao.IPostDao;
import com.HairStyle.springmvc.dao.IUserDao;
import com.HairStyle.springmvc.model.Collection;
import com.HairStyle.springmvc.model.Common;
import com.HairStyle.springmvc.model.Post_Type;
import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.model.Reply;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.like_table;
import com.HairStyle.springmvc.service.IPostService;
import com.HairStyle.springmvc.service.IUserService;


@Service
public class PostServiceImpl implements IPostService{
	
	@Resource
	private IPostDao postDao;
	
	public boolean Post_article(Map<String, Object> map){
		
		return postDao.post_articleDao(map);
		
	}
	
	public boolean Update_article(Map<String, Object> map){
		
		return postDao.update_articleDao(map);
		
	}
	
	public boolean insert_post_picture(Map<String, Object> map){
		return postDao.insert_post_picDao(map);
	}
	
	public boolean delete_post_picture(Map<String, Object> map){
		return postDao.delete_post_picDao(map);
	}
	
	public Post_Type getPostContentByType(Map<String, Object> map){
		
		return postDao.findPostByPost_typeDao(map);
		
	}
	
	public User getPostContentByUser_id(Map<String, Object> map){
	
		return postDao.findPostByUser_idDao(map);
	}

	
	public boolean Delete_article(String post_id){
		
			return postDao.delete_articleDao(post_id);
	}
	
	public Poster getPostByPost_id(Map<String, Object> map){
			return postDao.findPostByPost_idDao(map);
	}
	
	public boolean makecommon(Common common){
		
		return postDao.makeCommonDao(common);
	}
	
	public boolean deletecommon(String common_id){
		return postDao.delete_commonDao(common_id);
	}
	
	public boolean makereply(Reply reply){
		return postDao.makeReplyDao(reply);
	}
	
	public boolean deletereply(String reply_id){
		return postDao.delete_ReplyDao(reply_id);
	}

	public boolean search_post_picture(Map<String, Object> mapforcancelpic) {
		// TODO Auto-generated method stub
		if(postDao.search_post_picDao(mapforcancelpic)!=null)
			return true;
		else return false;
	}

	public boolean makelike(like_table lt) {
		// TODO Auto-generated method stub
		return postDao.makelikeDao(lt);
	}

	public boolean deletelike(String like_id) {
		// TODO Auto-generated method stub
		return postDao.deletelikeDao(like_id);
	}

	public boolean makecollect(Collection ct) {
		// TODO Auto-generated method stub
		return postDao.makecollectDao(ct);
	}

	public boolean deletecollect(String collect_id) {
		// TODO Auto-generated method stub
		return postDao.deletecollectDao(collect_id);
	}
	
	public User getuser_likeByUser_id(Map<String, Object> map){
		
		return postDao.getuser_likeDao(map);
	}
	
	public User getuser_collectByUser_id(Map<String, Object> map){
		
		return postDao.getuser_collectDao(map);
	}
}
