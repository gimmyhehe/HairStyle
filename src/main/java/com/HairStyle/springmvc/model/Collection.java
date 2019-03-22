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
public class Collection implements Serializable{
	
	
	private String collect_id;
	
	
	private String collector_id;

	
	private String collect_post_id;
	
	
	private Date collect_time;
	
	private Poster post;
    
    public String getCollect_id() {
		return collect_id;
	}

	public void setCollect_id(String collect_id) {
		this.collect_id = collect_id;
	}

	public String getCollector_id() {
		return collector_id;
	}

	public void setCollector_id(String collector_id) {
		this.collector_id = collector_id;
	}
	
	public String getCollect_post_id() {
		return collect_post_id;
	}

	public void setCollect_post_id(String collect_post_id) {
		this.collect_post_id = collect_post_id;
	}
	
	public Date getcollect_time() {
		return collect_time;
	}

	public void setcollect_time(Date collect_time) {
		this.collect_time = collect_time;
	}
	
	public Poster getPoster() {
		return post;
	}

	public void setPoster(Poster post) {
		this.post = post;
	}
	
	@Override
	public String toString() {
		return "Collection [collect_id=" + collect_id + ", collector_id" + collector_id
				+ ", collect_post_id=" + collect_post_id + ", collect_time=" + collect_time 
				+ ", post=" + post +"]";
	}
}