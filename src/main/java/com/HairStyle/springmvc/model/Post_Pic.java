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
public class Post_Pic implements Serializable{
	
	
	private String idpost_pic;
	
	
	private String seq_id;

	
	private String post_pic_dir;

	
	private String pic_post_id;
	
	private MultipartFile pic_file;
	
    
    public String getIdpost_pic() {
		return idpost_pic;
	}

	public void setIdpost_pic(String idpost_pic) {
		this.idpost_pic = idpost_pic;
	}

	public String getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(MultipartFile pic_file) {
		this.pic_file = pic_file;
	}
	
	public MultipartFile getPic_file() {
		return pic_file;
	}

	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	
	public String getPost_pic_dir() {
		return post_pic_dir;
	}

	public void setPost_pic_dir(String post_pic_dir) {
		this.post_pic_dir = post_pic_dir;
	}
	
	public String getPic_post_id() {
		return pic_post_id;
	}

	public void setPic_post_id(String pic_post_id) {
		this.pic_post_id = pic_post_id;
	}
	


	
	@Override
	public String toString() {
		return "Post_Pic [idpost_pic=" + idpost_pic + ", seq_id=" + seq_id
				+ ", post_pic_dir=" + post_pic_dir + ", pic_post_id=" + pic_post_id +", pic_file="+pic_file+"]";
	}

}
