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



public class User implements Serializable {
	
	
	private String user_id;
	
	
	private String user_name;

	
	private String password;
	
	
	private String new_password;

	
	private String gender;

	
    
	private Date birth_date;

	
	private String email;

	
	private String phone_number;

	
	private String phone_area;

	
	private String face_type;
	
	
	private String career;
	
	
	
	private Date create_time;
	

    private List<Poster> posters;
	

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
	
	public String getNew_Password() {
		return new_password;
	}

	public void setNew_Password(String new_password) {
		this.new_password = new_password;
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

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
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
	
	public Date getCreate_time() {

        return create_time;
    }

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public List<Poster> getPosters() {
        return posters;
    }
 
    public void setStudents(List<Poster> posters) {
        this.posters = posters;
    }


	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name" + user_name + "new_password=" + new_password 
				+ ", gender=" + gender + ", birth_date=" + birth_date + ", email=" + email + "password=" + password
				+ ", phone_area=" + phone_area + ", phone_number=" + phone_number
				+ ", face_type=" + face_type + ", career=" + career
				+ ", create_time="+ create_time
				+ ", posters="+ posters + "]";
	}


}
