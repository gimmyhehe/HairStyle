package com.HairStyle.springmvc.service.impl;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;
import com.HairStyle.springmvc.model.User;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.HairStyle.springmvc.service.ICompanyService;
import com.HairStyle.springmvc.dao.ICompanyDao;



@Service
public class CompanyServiceImpl implements ICompanyService{
	
	@Resource
	private ICompanyDao companyDao;
	
	public boolean regicompany(Company cp) {
		if(companyDao.regicompanyDao(cp)){
			return true;
		}
		else return false;
	}

	public boolean modifycompany(Company cp) {
		if(companyDao.modifycompanyDao(cp)){
			return true;
		}
		else return false;
	}

	public boolean regiHairstyle(Hairstyler hs) {
		// TODO Auto-generated method stub
		if(companyDao.regiHairstyleDao(hs)){
			return true;
		}
		else return false;
	}
	
	public boolean modifyHairstyle(Hairstyler hs) {
		// TODO Auto-generated method stub
		if(companyDao.modifyHairstyleDao(hs)){
			return true;
		}
		else return false;
	}

	public boolean deleteHairstyle(String hairstyle_id) {
		// TODO Auto-generated method stub
		if(companyDao.deleteHairstyleDao(hairstyle_id)){
			return true;
		}
		else return false;
	}

	public Company getHairstylist(String company_id) {
		// TODO Auto-generated method stub
		return companyDao.getHairstylistDao(company_id);
	}

	public Hairstyler getHairstyle(String hairstyle_id) {
		// TODO Auto-generated method stub
		return companyDao.getHairstyleDao(hairstyle_id);
	}
	
	public Integer addproduct(Product product) {
		// TODO Auto-generated method stub
		return companyDao.addproductDao(product);
	}

	public boolean addproduct_pic(Product_Pic pp) {
		// TODO Auto-generated method stub
		return companyDao.addproduct_picDao(pp);
	}

	public boolean search_product_pic(Map<String, Object> mapforcancelpic) {
		// TODO Auto-generated method stub
		if(companyDao.search_product_picDao(mapforcancelpic)!=null)
			return true;
		else return false;
	}

	public boolean deleteproduct_pic(Map<String, Object> mapforcancelpic) {
		// TODO Auto-generated method stub
		return companyDao.deleteproduct_picDao(mapforcancelpic);
	}

	public boolean modifyproduct(Product product) {
		// TODO Auto-generated method stub
		return companyDao.modifyproductDao(product);
	}

	public Company findproductbycom(String company_id) {
		// TODO Auto-generated method stub
		return companyDao.findproductbycomDao(company_id);
	}

	public User scan_business(String company_id) {
		// TODO Auto-generated method stub
		return companyDao.scan_businessDao(company_id);
	}



	
}
