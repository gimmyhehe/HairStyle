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
public class Common_Order implements Serializable{
	
	private String com_order_id;
	private String common_order_id;
	private int score;
	private String common_order_content;
	private Date common_time;
	private boolean is_reply;
	private Reply_Order ro=new Reply_Order();
	private List<Pic_Common_Order> lpco;
	
	public void setLPCO(List<Pic_Common_Order> lpco) {
		this.lpco = lpco;
	}
	
	public List<Pic_Common_Order> getLPCO() {
		return lpco;
	}
	
	public void setRO(Reply_Order ro) {
		this.ro = ro;
	}
	
	public Reply_Order getRO() {
		return ro;
	}
	
	
	public void setCom_order_id(String com_order_id) {
		this.com_order_id = com_order_id;
	}
	
	public String getCom_order_id() {
		return com_order_id;
	}
	
	public void setCommon_order_id(String common_order_id) {
		this.common_order_id = common_order_id;
	}
	
	public String getCommon_order_id() {
		return common_order_id;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setCommon_order_content(String common_order_content) {
		this.common_order_content = common_order_content;
	}
	
	public String getCommon_order_content() {
		return common_order_content;
	}
	
	public void setCommon_time(Date common_time) {
		this.common_time = common_time;
	}
	
	public Date getCcommon_time() {
		return common_time;
	}
	
	public void setIs_reply(boolean is_reply) {
		this.is_reply = is_reply;
	}
	
	public boolean getIs_reply() {
		return is_reply;
	}
	
	@Override
	public String toString() {
		return "Common_Order [com_order_id=" + com_order_id + ", common_order_id=" + common_order_id
				+ ", score=" + score + ", common_order_content=" + common_order_content + ", common_time=" + common_time
				+", is_reply="+is_reply+ ", ro="+ro+", lpco="+lpco+"]";
	}
	
	
}
