package com.HairStyle.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.Common_Order;
import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Order;
import com.HairStyle.springmvc.model.Pic_Common_Order;
import com.HairStyle.springmvc.model.User;


public interface IOrderDao {

	public boolean makeorderDao(Order order);

	public boolean completeorderDao(Map<String, Object> order_info);

	public boolean cancelorderDao(String order_id);
	
	public boolean ordersuccessDao(String order_id);
	
	public User findorderbyuser_idDao(String user_id); 

	public Company findorderbycompany_idDao(String company_id);

	public boolean insert_commonorder_picDao(Pic_Common_Order pco);

	public boolean commonorderDao(Common_Order co); 

}
