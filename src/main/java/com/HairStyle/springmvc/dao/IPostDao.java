package com.HairStyle.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.Collection;
import com.HairStyle.springmvc.model.Common;
import com.HairStyle.springmvc.model.Post_Pic;
import com.HairStyle.springmvc.model.Post_Type;
import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.model.Reply;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.like_table;

@Repository
public interface IPostDao {

	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */

	public boolean post_articleDao(Map<String, Object> map) ;
	public boolean update_articleDao(Map<String, Object> map);
	public boolean delete_articleDao(Map<String, Object> map) ;
	public boolean insert_post_picDao(Map<String, Object> map) ;
	public boolean delete_post_picDao(Map<String, Object> map) ;
	public Post_Type findPostByPost_typeDao(Map<String, Object> map);
	public User findPostByUser_idDao(Map<String, Object> map);
	public Poster findPostByPost_idDao(Map<String, Object> map);
	public User scan_detailbusiness(String company_id);

	
	
	public boolean makeCommonDao(Common common) ;
	public boolean delete_commonDao(String common_id) ;
	public boolean makeReplyDao(Reply reply) ;
	public boolean delete_ReplyDao(String reply_id) ;
	public Post_Pic search_post_picDao(Map<String, Object> mapforcancelpic);
	public boolean makelikeDao(like_table lt);
	public boolean deletelikeDao(String like_id);
	public boolean makecollectDao(Collection ct);
	public boolean deletecollectDao(String collect_id);
	
	public User getuser_likeDao(Map<String, Object> map);
	public User getuser_collectDao(Map<String, Object> map);

	public List<Poster> findPostByPost_typeDao(String post_type);
	public List<Poster> findPostByUser_nameDao(String user_name);

	

}
