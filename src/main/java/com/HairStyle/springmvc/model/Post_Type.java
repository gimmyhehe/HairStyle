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
public class Post_Type implements Serializable{

	private String post_type;
	
	private List<Poster> poster;
    
    public String getPost_Type() {
		return post_type;
	}

	public void setPost_Type(String post_type) {
		this.post_type = post_type;
	}

	public List<Poster> getPoster() {
		return poster;
	}

	public void setPoster(List<Poster> poster) {
		this.poster = poster;
	}
	
	
	@Override
	public String toString() {
		return "Post_Type [post_type=" + post_type + ", poster" + poster+ "]";
	}

}
