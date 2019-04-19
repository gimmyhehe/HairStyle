package com.HairStyle.springmvc.service;

import java.util.Map;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;

public interface ICompanyService {

	public boolean regicompany(Company cp);
	public boolean modifycompany(Company cp);
	public boolean regiHairstyle(Hairstyler hs);
	public boolean modifyHairstyle(Hairstyler hs);
	public boolean deleteHairstyle(String hairstyle_id);
	
	public Company getHairstylist(String company_id);
	
	public Integer addproduct(Product product); 
	public boolean addproduct_pic(Product_Pic pp);
	public boolean search_product_pic(Map<String, Object> mapforcancelpic);
	public boolean deleteproduct_pic(Map<String, Object> mapforcancelpic);
	public boolean modifyproduct(Product product); 
	public Company findproductbycom(String company_id);
}
