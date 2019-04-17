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

public class Product_Pic implements Serializable {
	
	private String product_pic_id;
	private int product_seq_id;
	private String product_pic_dir;
	private int be_product_id;
	private boolean is_active;

	public String getProduct_pic_id() {
		return product_pic_id;
	}

	public void setProduct_pic_id(String product_pic_id) {
		this.product_pic_id = product_pic_id;
	}
	
	public int getProduct_seq_id() {
		return product_seq_id;
	}

	public void setProduct_seq_id(int product_seq_id) {
		this.product_seq_id = product_seq_id;
	}
	
	public String getProduct_pic_dir() {
		return product_pic_dir;
	}

	public void setProduct_pic_dir(String product_pic_dir) {
		this.product_pic_dir = product_pic_dir;
	}
	
	public int getbe_product_id() {
		return be_product_id;
	}

	public void setbe_product_id(int be_product_id) {
		this.be_product_id = be_product_id;
	}
	
	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	

	@Override
	public String toString() {
		return "Product_Pic [product_pic_id=" + product_pic_id + ", product_seq_id=" + product_seq_id + ", product_pic_dir=" + product_pic_dir + ",be_product_id=" + be_product_id 
				+ ", is_active=" + is_active + "]";
	}
}