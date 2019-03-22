package com.HairStyle.springmvc.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Poster implements Serializable{

	private String post_user_name;
	

	private String post_id;

	private String post_type;

	private String post_content;
	
	private String post_time;
	
	private int like_num;
	
	private int collect_num;
	
	private String last_edit_time;
	
	private User user;
	
	private List<Post_Pic> post_pic;
	
	private List<Common> common;
	
	private List<like_table> like_table;
	
	private int like_id;
	
	private int collect_id;
    
	public int getLike_id() {
		return like_id;
	}

	public void setCollect_id(int collect_id) {
		this.collect_id = collect_id;
	}
	
	public int getCollect_id() {
		return collect_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}
	
    public String getPost_user_name() {
		return post_user_name;
	}

	public void setPost_user_name(String post_user_name) {
		this.post_user_name = post_user_name;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	
	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	
	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}
	
	
	public String getLast_edit_time() {
		return last_edit_time;
	}

	public void setLast_edit_time(String last_edit_time) {
		this.last_edit_time = last_edit_time;
	}
	
	public List<Post_Pic> getPost_Pic() {
		return post_pic;
	}

	public void setPost_Pic(List<Post_Pic> post_pic) {
		this.post_pic = post_pic;
	}
	
	public List<Common> getCommon() {
		return common;
	}

	public void setCommon(List<Common> common) {
		this.common = common;
	}
	
	public List<like_table> getLiketable() {
		return like_table;
	}

	public void setLiketable(List<like_table> like_table) {
		this.like_table = like_table;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
	public int getLike_num(){
		return like_num;
	}
	
	public void setLike_num(int like_num){
		this.like_num=like_num;
	}
	
	public int getCollect_num(){
		return collect_num;
	}
	
	public void setCollect_num(int collect_num){
		this.collect_num=collect_num;
	}
	
	@Override
	public String toString() {
		return "Poster [post_user_name=" + post_user_name + ", post_id" + post_id
				+ ", post_type=" + post_type + ", post_content=" + post_content + ", post_time=" + post_time
				+", last_edit_time="+last_edit_time+", post_pic="+post_pic+", common="+common+",user="+user
				+", like_table=" + like_table + ", like_num="+like_num+", like_num="+like_num+ ", like_id="+like_id+", collect_id="+collect_id+"]";
	}

}
