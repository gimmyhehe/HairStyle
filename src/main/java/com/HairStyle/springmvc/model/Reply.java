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
public class Reply implements Serializable{
	
	
	private String reply_id;
	
	private String reply_common_id;
	
	private String reply_user_id;
	private String reply_user_name;
	
	private String replyed_user_id;
	
	private String replyed_user_name;
	
	private String reply_content;
	
	private Date reply_time;
	
    
    public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	
	public String getReply_common_id() {
		return reply_common_id;
	}

	public void setReply_common_id(String reply_common_id) {
		this.reply_common_id = reply_common_id;
	}
	
	public String getReply_user_id() {
		return reply_user_id;
	}

	public void setReply_user_id(String reply_user_id) {
		this.reply_user_id = reply_user_id;
	}
	
	public String getReply_user_name() {
		return replyed_user_name;
	}

	public void setReply_user_name(String reply_user_name) {
		this.reply_user_name = reply_user_name;
	}
	
	public String getReplyed_user_id() {
		return replyed_user_id;
	}
	
	public void setReplyed_user_id(String replyed_user_id) {
		this.replyed_user_id = replyed_user_id;
	}
	
	public String getReplyed_user_name() {
		return replyed_user_name;
	}
	
	public void setReplyed_user_name(String replyed_user_name) {
		this.replyed_user_name = replyed_user_name;
	}
	
	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_time() {
		return reply_time;
	}

	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
	}
	
	

	
	@Override
	public String toString() {
		return "Reply [reply_id=" + reply_id + ", reply_common_id=" + reply_common_id + ", reply_user_id=" + reply_user_id 
				+ ", replyed_user_id=" + replyed_user_id + ", reply_content=" + reply_content
				+ ", replyed_user_name=" + replyed_user_name 
				+ ", reply_user_name=" + reply_user_name 
				+ ", reply_time="+ reply_time+ "]";
	}
}