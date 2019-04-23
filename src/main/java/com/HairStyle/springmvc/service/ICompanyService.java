package com.HairStyle.springmvc.service;

import java.util.List;
import java.util.Map;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;
import com.HairStyle.springmvc.model.User;

public interface ICompanyService {

	public boolean regicompany(Company cp);
	public boolean modifycompany(Company cp);
	public boolean regiHairstyle(Hairstyler hs);
	public boolean modifyHairstyle(Hairstyler hs);
	public boolean deleteHairstyle(String hairstyle_id);
	
	public Company getHairstylist(String company_id);
	public Hairstyler getHairstyle(String hairstyle_id);
	public Integer addproduct(Product product); 
	public boolean addproduct_pic(Product_Pic pp);
	public boolean search_product_pic(Map<String, Object> mapforcancelpic);
	public boolean deleteproduct_pic(Map<String, Object> mapforcancelpic);
	public boolean modifyproduct(Product product); 
	public Company findproductbycom(String company_id);
	public User scan_business(String company_id);
	public Product getoneproduct(String product_id);
	public List<Company> search_business_loca(Map<String, String> location,int currPage, int pageSize);
}
