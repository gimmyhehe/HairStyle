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
public class Order implements Serializable{
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int hairstyle_id;
	
	private String order_id;
	
	private String user_phone;
	
	private int order_product_id;
	
	private String order_user_id;
	
	private int product_amount;
	
	private double order_price;
	
	private Date order_time;
	
	private Date order_create_time;
	
	private Date order_finish_time;
	
	private String other_info;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private boolean is_common;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private boolean is_active;
	private User user;
	private Product product;
	private Hairstyler hairstyle;
	private List<Common_Order> LCO;
	
	public List<Common_Order> getLCO() {
		return LCO;
	}

	public void setLCO(List<Common_Order> LCO) {
		this.LCO = LCO;
	}
	
	
	public Hairstyler getHairstyle() {
		return hairstyle;
	}

	public void setHairstyle(Hairstyler hairstyle) {
		this.hairstyle = hairstyle;
	}
	
	public int getHairstyle_id() {
		return hairstyle_id;
	}

	public void setHairstyle_id(int hairstyle_id) {
		this.hairstyle_id = hairstyle_id;
	}
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone =user_phone;
	}
	
	public String getOther_info() {
		return other_info;
	}

	public void setOther_info(String other_info) {
		this.other_info =other_info;
	}
	
	public int getOrder_product_id() {
		return order_product_id;
	}

	public void setOrder_product_id(int order_product_id) {
		this.order_product_id = order_product_id;
	}
	
	public String getOrder_user_id() {
		return order_user_id;
	}

	public void setOrder_user_id(String order_user_id) {
		this.order_user_id = order_user_id;
	}
	
	public int getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(int order_product_id) {
		this.product_amount = product_amount;
	}
	
	public double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	
	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	
	public Date getOrder_create_time() {
		return order_create_time;
	}

	public void setOrder_create_time(Date order_create_time) {
		this.order_create_time = order_create_time;
	}
	
	public Date getOrder_finish_time() {
		return order_finish_time;
	}

	public void setOrder_finish_time(Date order_finish_time) {
		this.order_finish_time = order_finish_time;
	}
	
	public boolean getIs_common() {
		return is_common;
	}

	public void setIs_common(boolean is_common) {
		this.is_common = is_common;
	}
	
	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_product_id" + order_product_id
				+ ", order_user_id=" + order_user_id + ", product_amount=" + product_amount + ", order_price=" + order_price
				+", order_create_time="+order_create_time+", order_finish_time="+order_finish_time+", is_common="+is_common+",is_active="+is_active
				+", user="+user+", product="+product+", hairstyle_id="+hairstyle_id+", order_time="+order_time+", other_info="+other_info+", user_phone="+user_phone
				+", LCO="+LCO+"]";
	}
	   
}
