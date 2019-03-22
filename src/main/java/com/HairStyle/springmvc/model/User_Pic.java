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
public class User_Pic implements Serializable{
	
	
	private String pic_id;
		
	private String uploader_id;
	
	private Date pic_date;
	
	private String user_pic_dir;
	   
    public String getPic_id() {
		return pic_id;
	}

	public void setPic_id(String pic_id) {
		this.pic_id = pic_id;
	}

	public String getUploader_id() {
		return uploader_id;
	}

	public void setUploader_id(String uploader_id) {
		this.uploader_id = uploader_id;
	}
	
	public String getUser_pic_dir() {
		return user_pic_dir;
	}

	public void setUser_pic_dir(String user_pic_dir) {
		this.user_pic_dir = user_pic_dir;
	}
	
	public Date getPic_date() {
		return pic_date;
	}

	public void setPic_date(Date pic_date) {
		this.pic_date = pic_date;
	}
	

	
	@Override
	public String toString() {
		return "User_Pic [pic_id=" + pic_id + ", uploader_id=" + uploader_id
				+ ", user_pic_dir=" + user_pic_dir + ", pic_date=" + pic_date +"]";
	}

}