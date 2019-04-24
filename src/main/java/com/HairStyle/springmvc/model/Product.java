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

public class Product implements Serializable {
	
	private int product_id;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int product_company_id;
	
	private String product_name;
	private double product_price;
	private String product_intr;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int product_amount;
	private Date product_time;
	private List<Product_Pic> pp;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private boolean is_active;
	private List<Order> order;
	private Company company;
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public int getProduct_company_id() {
		return product_company_id;
	}

	public void setProduct_company_id(int product_company_id) {
		this.product_company_id = product_company_id;
	}
	
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	
	public String getProduct_intr() {
		return product_intr;
	}

	public void setProduct_intr(String product_intr) {
		this.product_intr = product_intr;
	}
	
	public int getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}
	
	public Date getProduct_time() {
		return product_time;
	}

	public void setProduct_time(Date product_time) {
		this.product_time = product_time;
	}
	
	public List<Product_Pic> getProduct_Pic() {
		return pp;
	}

	public void setProduct_Pic(List<Product_Pic> pp) {
		this.pp = pp;
	}
	
	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	public Company Company() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_company_id" + product_company_id + ", product_name" + product_name + ",product_price=" + product_price 
				+ ", product_intr=" + product_intr + ", product_amount=" + product_amount + ",product_time="+product_time+ ", pp="+pp+ ", is_active="+is_active
				+ ", order=" + order +", company=" + company +"]";
	}
}
