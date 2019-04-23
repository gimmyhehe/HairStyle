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
public class Reply_Order implements Serializable{
	
	
	private String order_reply_id;
	
	private String reply_o_common_id;
	
	private String order_reply_content;
	
	private Date order_reply_time;
    
    public String getOrder_reply_id() {
		return order_reply_id;
	}

	public void setOrder_reply_id(String order_reply_id) {
		this.order_reply_id = order_reply_id;
	}
	
	public String getReply_o_common_id() {
		return reply_o_common_id;
	}

	public void setReply_o_common_id(String reply_o_common_id) {
		this.reply_o_common_id = reply_o_common_id;
	}
	
	public String getOrder_reply_content() {
		return order_reply_content;
	}

	public void setOrder_reply_content(String order_reply_content) {
		this.order_reply_content = order_reply_content;
	}
	
	public Date getorder_reply_time() {
		return order_reply_time;
	}

	public void setorder_reply_time(Date order_reply_time) {
		this.order_reply_time = order_reply_time;
	}
	
	
	@Override
	public String toString() {
		return "Reply_Order [order_reply_id=" + order_reply_id + ", reply_o_common_id=" + reply_o_common_id + ", order_reply_content=" + order_reply_content 
				+ ", order_reply_time=" + order_reply_time + "]";
	}
	
}