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

public class Poster implements Serializable{
	@Size(min=3, max=30)
	private String post_user_name;
	
	@Size(min=3, max=30)
	private String post_id;

	@Size(min=3, max=10)
	private String post_type;

	@NotEmpty
	private String post_content;
	
	@NotEmpty
	private String post_time;
	

	private String last_edit_time;
	
	private List<Post_Pic> post_pic;
    
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
	
	@Override
	public String toString() {
		return "Poster [post_user_name=" + post_user_name + ", post_id" + post_id
				+ ", post_type=" + post_type + ", post_content=" + post_content + ", post_time=" + post_time
				+"last_edit_time="+last_edit_time+"post_pic"+post_pic+"]";
	}

}
