package com.HairStyle.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.HairStyle.springmvc.model.Order;


public interface IOrderDao {

	public boolean makeorderDao(Order order);

	public boolean completeorderDao(Map<String, Object> order_info);

	public boolean cancelorderDao(String order_id); 


}
