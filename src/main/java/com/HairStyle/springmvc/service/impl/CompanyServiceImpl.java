package com.HairStyle.springmvc.service.impl;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;

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
		if(companyDao.regiHairstyleDao(hs)){
			return true;
		}
		else return false;
	}
}
