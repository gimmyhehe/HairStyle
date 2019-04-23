package com.HairStyle.springmvc.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.python.bouncycastle.asn1.dvcs.Data;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Pic_Common_Order implements Serializable {
	
	private String p_c_o_id;
	private String p_c_o_seq_id;
	private String p_c_o_dir;
	private String p_c_o_common_id;
	


	public String getP_c_o_id() {
		return p_c_o_id;
	}

	public void setP_c_o_id(String p_c_o_id) {
		this.p_c_o_id = p_c_o_id;
	}
	
	public String getP_c_o_seq_id() {
		return p_c_o_seq_id;
	}

	public void setP_c_o_seq_id(String p_c_o_seq_id) {
		this.p_c_o_seq_id = p_c_o_seq_id;
	}
	
	public String getP_c_o_dir() {
		return p_c_o_dir;
	}

	public void setP_c_o_dir(String p_c_o_dir) {
		this.p_c_o_dir = p_c_o_dir;
	}
	
	public String getp_c_o_common_id() {
		return p_c_o_common_id;
	}

	public void setp_c_o_common_id(String p_c_o_common_id) {
		this.p_c_o_common_id = p_c_o_common_id;
	}
	

	@Override
	public String toString() {
		return "Pic_Common_Order [p_c_o_id=" + p_c_o_id + ", p_c_o_seq_id=" + p_c_o_seq_id + ", p_c_o_dir=" + p_c_o_dir + ",p_c_o_common_id=" + p_c_o_common_id 
				+ "]";
	}
}
