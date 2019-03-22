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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class like_table implements Serializable{
	
	
	private String like_id;
	
	
	private String like_post_id;

	private String liker_name;
	
	private String like_user_id;
	
	private String liker_pic;
	
	private Date like_time;
	
	private Poster post;
    
    public String getLike_id() {
		return like_id;
	}

	public void setLike_id(String like_id) {
		this.like_id = like_id;
	}

	public String getLike_user_id() {
		return like_user_id;
	}

	public void setLike_user_id(String like_user_id) {
		this.like_user_id = like_user_id;
	}
	
	public String getLike_post_id() {
		return like_post_id;
	}

	public void setLike_post_id(String like_post_id) {
		this.like_post_id = like_post_id;
	}
	
	public Date getLike_time() {
		return like_time;
	}

	public void setLike_time(Date like_time) {
		this.like_time = like_time;
	}
	
	public String getLiker_name() {
		return liker_name;
	}

	public void setLiker_name(String liker_name) {
		this.liker_name = liker_name;
	}
	
	public String getLiker_pic() {
		return liker_pic;
	}

	public void setLiker_pic(String liker_pic) {
		this.liker_pic = liker_pic;
	}
	
	public Poster getPoster() {
		return post;
	}

	public void setPoster(Poster post) {
		this.post = post;
	}
	
	@Override
	public String toString() {
		return "like_table [like_id=" + like_id + ", like_id" + like_id
				+ ", like_post_id=" + like_post_id + ", like_time=" + like_time 
				+ ", liker_name=" + liker_name+", liker_pic=" + liker_pic
				+ ", post=" + post+"]";
	}
}