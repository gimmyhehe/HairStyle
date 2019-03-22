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
public class Common implements Serializable{
	
	
	private String common_id;
	
	
	private String common_post_id;

	
	private String common_user_id;
	
	private String common_user_name;

	private String common_user_pic;
	private String common_content;
	
	
	private Date common_time;
	
	private List<Reply> reply;
	
    
    public String getCommon_id() {
		return common_id;
	}

	public void setCommon_id(String common_id) {
		this.common_id = common_id;
	}

	public String getCommon_Post_id() {
		return common_post_id;
	}

	public void setCommon_Post_id(String common_post_id) {
		this.common_post_id = common_post_id;
	}
	
	public String getCommon_User_id() {
		return common_user_id;
	}

	public void setCommon_User_id(String common_user_id) {
		this.common_user_id = common_user_id;
	}
	
	public String getCommon_content() {
		return common_content;
	}

	public void setCommon_content(String common_content) {
		this.common_content = common_content;
	}
	
	public Date Common_time() {
		return common_time;
	}

	public void setCommon_time(Date common_time) {
		this.common_time = common_time;
	}
	
	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	
	public String getCommon_User_name() {
		return common_user_name;
	}

	public void setCommon_User_name(String common_user_name) {
		this.common_user_name = common_user_name;
	}
	
	public String getCommon_User_pic() {
		return common_user_pic;
	}

	public void setCommon_User_pic(String common_user_pic) {
		this.common_user_pic = common_user_pic;
	}
	
	@Override
	public String toString() {
		return "Common [common_id=" + common_id + ", common_post_id" + common_post_id
				+ ", common_user_id=" + common_user_id + ", common_content=" + common_content 
				+ ", common_time=" + common_time +", reply=" + reply +", common_user_name=" + common_user_name 
				+", common_user_pic=" + common_user_pic+"]";
	}
}