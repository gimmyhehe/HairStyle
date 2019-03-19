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



public class User implements Serializable {
	
	@Size(min=3, max=30)
	private String user_id;
	
	@Size(min=3, max=30)
	private String user_name;

	@Size(min=3, max=30)
	private String password;

	@NotEmpty
	private String gender;

	
    @NotNull
	private String birth_date;

	@Email @NotEmpty
	private String email;

	@NotEmpty
	private String phone_number;

	@NotEmpty
	private String phone_area;

	@NotEmpty
	private String face_type;
	
	@NotEmpty
	private String career;
	
	
	@NotNull
	private String create_date;
	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getPhone_area() {
		return phone_area;
	}
	
	public void setPhone_area(String phone_area) {
		this.phone_area = phone_area;
	}

	public String getFace_type() {
		return face_type;
	}
	
	public void setFace_type(String face_type) {
		this.face_type = face_type;
	}

	public String getCareer() {
		return career;
	}
	
	public void setCareer(String career) {
		this.career = career;
	}
	
	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String Create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name" + user_name
				+ ", gender=" + gender + ", birth_date=" + birth_date + ", email=" + email
				+ ", phone_area=" + phone_area + ", phone_number=" + phone_number
				+ ", face_type=" + face_type + ", career=" + career
				+ "create_date="+create_date+"]";
	}


}
