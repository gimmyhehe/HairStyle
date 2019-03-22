package com.HairStyle.springmvc.service;

import java.util.Map;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;

public interface ICompanyService {

	public boolean regicompany(Company cp);
	public boolean modifycompany(Company cp);
	public boolean regiHairstyle(Hairstyler hs);
	public boolean modifyHairstyle(Hairstyler hs);

}
