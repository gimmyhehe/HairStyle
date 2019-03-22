package com.HairStyle.springmvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.HairStyle.springmvc.dao.IPostDao;
import com.HairStyle.springmvc.dao.IUserDao;
import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.IPostService;


@Service
public class PostServiceImpl {
	
	@Resource
	private IPostDao postDao;
	
	public boolean post_articleDao(Map<String, Object> map){
		
		return postDao.post_articleDao(map);
		
	}
	
	public boolean insert_post_picture(Map<String, Object> map){
		return postDao.insert_post_pic(map);
	}
	
	public List<Poster> getPostContentByType(String str,int i){
		if (i==1){
			return postDao.findPostByPost_typeDao(str);
		}
		else{
			return postDao.findPostByUser_nameDao(str);
		}
	}
}
