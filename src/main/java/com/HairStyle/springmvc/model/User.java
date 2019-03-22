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
	
	private String country;
	
	private String province;
	
	private String area;
	
	private int user_type;
	
	private User_Pic user_pic;
	
	private Date create_time;
	
	private Company company;
	
	private MultipartFile user_img;
    private List<Poster> posters;
    private List<like_table> lt;
    private List<Collection> ct;

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
	
	public void setUser_Pic(User_Pic user_pic) {
		this.user_pic = user_pic;
	}

	public User_Pic getUser_Pic() {
		return user_pic;
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
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getArea() {
		return area;
	}	
	
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	public int getUser_type() {
		return user_type;
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
 
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }
    
    public List<like_table> getLT() {
        return lt;
    }
 
    public void setLT(List<like_table> lt) {
        this.lt = lt;
    }
    
    public List<Collection> getCT() {
        return ct;
    }
 
    public void setCT(List<Collection> ct) {
        this.ct = ct;
    }

    public MultipartFile getUser_img() {
        return user_img;
    }
 
    public void setUser_img(MultipartFile user_img) {
        this.user_img = user_img;
    }

    public void setCompany(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return company;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name" + user_name + "new_password=" + new_password 
				+ ", gender=" + gender + ", birth_date=" + birth_date + ", email=" + email + "password=" + password
				+ ", phone_area=" + phone_area + ", phone_number=" + phone_number
				+ ", face_type=" + face_type + ", career=" + career
				+ ", create_time="+ create_time
				+ ", posters="+ posters + ", user_pic="+ user_pic+ ", country=" +country
				+ ", province=" + province + ", area=" +area + ",user_type="+user_type
				+", user_img=" + user_img + ", lt=" + lt +", ct=" + ct +", company=" + company +"]";
	}


}
