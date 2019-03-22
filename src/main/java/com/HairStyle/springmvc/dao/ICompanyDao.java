package com.HairStyle.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;

@Repository
public interface ICompanyDao {
	
	public boolean regicompanyDao(Company cp);
	public boolean modifycompanyDao(Company cp);
	public boolean regiHairstyleDao(Hairstyler hs);
	public boolean modifyHairstyleDao(Hairstyler hs);
	
}
