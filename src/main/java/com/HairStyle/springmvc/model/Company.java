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

public class Company implements Serializable {
	
	private int company_id;
	private String company_user_id;
	private String company_name;
	private String company_intr;
	private String location;
	private List<Hairstyler> lh;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private List<Product> LP;
	
	private int score;
	private int totalbuyer;
	private int totalcommont;
	
	public int getTotalcommont() {
		return totalcommont;
	}

	public void setTotalcommont(int totalcommont) {
		this.totalcommont = totalcommont;
	}
	
	public int getTotalbuyer() {
		return totalbuyer;
	}

	public void setTotalbuyer(int totalbuyer) {
		this.totalbuyer = totalbuyer;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public String getCompany_user_id() {
		return company_user_id;
	}

	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}
	
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public String getCompany_intr() {
		return company_intr;
	}

	public void setCompany_intr(String company_intr) {
		this.company_intr = company_intr;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Hairstyler> getHairstylist() {
		return lh;
	}

	public void setHairstylist(List<Hairstyler> lh) {
		this.lh = lh;
	}
	
	public List<Product> getProductlist() {
		return LP;
	}

	public void setProductlist(List<Product> LP) {
		this.LP = LP;
	}
	
	@Override
	public String toString() {
		return "Company [company_id=" + company_id + ", company_name" + company_name + ", company_user_id" + company_user_id + ",company_intr=" + company_intr 
				+ ", location=" + location + ", lh=" + lh + ", LP=" + LP +
				", score=" + score + ", totalbuyer=" + totalbuyer + ", totalcommont=" + totalcommont +"]";
	}

}
