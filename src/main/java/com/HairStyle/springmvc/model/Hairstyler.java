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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Hairstyler implements Serializable {
	
	private int hairstyle_id;
	private String hairstyle_name;
	private String hairstyle_intr;
	private String hairstyle_level;
	private String hairstyle_company_id;
	private String hairstyle_pic;
	
	public int getHairstyle_id() {
		return hairstyle_id;
	}

	public void setHairstyle_id(int hairstyle_id) {
		this.hairstyle_id = hairstyle_id;
	}
	
	public String getHairstyle_name() {
		return hairstyle_name;
	}

	public void setHairstyle_name(String hairstyle_name) {
		this.hairstyle_name = hairstyle_name;
	}
	
	public String getHairstyle_intr() {
		return hairstyle_intr;
	}

	public void setHairstyle_intr(String hairstyle_intr) {
		this.hairstyle_intr = hairstyle_intr;
	}
	
	public String getHairstyle_level() {
		return hairstyle_level;
	}

	public void setHairstyle_level(String hairstyle_level) {
		this.hairstyle_level = hairstyle_level;
	}
	
	public String getHairstyle_company_id() {
		return hairstyle_company_id;
	}

	public void setHairstyle_company_id(String hairstyle_company_id) {
		this.hairstyle_company_id = hairstyle_company_id;
	}
	
	public String getHairstyle_pic() {
		return hairstyle_company_id;
	}

	public void setHairstyle_pic(String hairstyle_pic) {
		this.hairstyle_pic = hairstyle_pic;
	}
	
	
	@Override
	public String toString() {
		return "Company [hairstyle_id=" + hairstyle_id + ", hairstyle_name" + hairstyle_name + ", hairstyle_intr" + hairstyle_intr + ", hairstyle_level =" + hairstyle_level 
				+ ", hairstyle_company_id=" + hairstyle_company_id + ", hairstyle_pic =" + hairstyle_pic 
				+ "]";
	}
}
