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

public class Poster implements Serializable{
	@Size(min=3, max=30)
	private String post_user_name;
	
	@Size(min=3, max=30)
	private String post_id;

	@Size(min=3, max=10)
	private String post_type;

	@NotEmpty
	private String post_content;
	
	@NotEmpty
	private String post_time;
	
	private String pic_one;    
    
    private String pic_two;    
    
    private String pic_three;    
    
    private String pic_four;
    
    private String pic_five;
    
    private String pic_six;
    
    private String pic_seven;
    
    private String pic_eight;
    
    private String pic_nine;


	private String last_edit_time;
    
    public String getPost_user_name() {
		return post_user_name;
	}

	public void setPost_user_name(String post_user_name) {
		this.post_user_name = post_user_name;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	
	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	
	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}

	public String getPic_one() {
		return pic_one;
	}

	public void setPic_one(String pic_one) {
		this.pic_one = pic_one;
	}
	
	public String getPic_two() {
		return pic_two;
	}

	public void setPic_two(String pic_two) {
		this.pic_one = pic_two;
	}
	
	public String getPic_three() {
		return pic_three;
	}

	public void setPic_three(String pic_three) {
		this.pic_three = pic_three;
	}
	
	public String getPic_four() {
		return pic_four;
	}

	public void setPic_four(String pic_four) {
		this.pic_four = pic_four;
	}
	
	public String getPic_five() {
		return pic_five;
	}

	public void setPic_five(String pic_five) {
		this.pic_five = pic_five;
	}
	
	public String getPic_six() {
		return pic_six;
	}

	public void setPic_six(String pic_six) {
		this.pic_six = pic_six;
	}
	
	public String getPic_seven() {
		return pic_seven;
	}

	public void setPic_seven(String pic_seven) {
		this.pic_seven = pic_seven;
	}
	
	public String getPic_eight() {
		return pic_eight;
	}

	public void setPic_eight(String pic_eight) {
		this.pic_eight = pic_eight;
	}
	
	public String getPic_nine() {
		return pic_nine;
	}

	public void setPic_nine(String pic_nine) {
		this.pic_nine = pic_nine;
	}
	
	public String getLast_edit_time() {
		return last_edit_time;
	}

	public void setLast_edit_time(String last_edit_time) {
		this.last_edit_time = last_edit_time;
	}
	
	@Override
	public String toString() {
		return "Poster [post_user_name=" + post_user_name + ", post_id" + post_id
				+ ", post_type=" + post_type + ", post_content=" + post_content + ", post_time=" + post_time
				+ ", pic_one=" + pic_one + ", pic_two=" + pic_two
				+ ", pic_three=" + pic_three + ", pic_four=" + pic_four
				+ "pic_five="+pic_five+"pic_six="+pic_six+"pic_seven="+pic_seven+"pic_eight="+pic_eight
				+"pic_nine="+pic_nine+"last_edit_time="+last_edit_time+"]";
	}

}
