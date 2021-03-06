package com.HairStyle.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;
import com.HairStyle.springmvc.model.User;

@Repository
public interface ICompanyDao {
	
	public boolean regicompanyDao(Company cp);
	public boolean modifycompanyDao(Company cp);
	public boolean regiHairstyleDao(Hairstyler hs);
	public boolean modifyHairstyleDao(Hairstyler hs);
	public boolean deleteHairstyleDao(String hairstyle_id);
	public Company getHairstylistDao(String company_id);
	public Integer addproductDao(Product product);
	public boolean addproduct_picDao(Product_Pic pp);
	public Product_Pic search_product_picDao(Map<String, Object> mapforcancelpic);
	public boolean deleteproduct_picDao(Map<String, Object> mapforcancelpic);
	public boolean modifyproductDao(Product product);
	public Company findproductbycomDao(String company_id);
	public Hairstyler getHairstyleDao(String hairstyle_id);
	public User scan_businessDao(String company_id);
	public Product getoneproductDao(String product_id);
	public List<Company> search_business_locaDao(Map<String, String> location);
	public User scan_detailbusinessDao(String company_id);
	public Product getoneproductdetailDao(String product_id);


}
