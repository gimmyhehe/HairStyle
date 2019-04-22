package com.HairStyle.springmvc.service;

import java.util.Map;

import com.HairStyle.springmvc.model.Order;

public interface IOrderService {
	public boolean completeorder(Map<String, Object> order_info);
	public boolean makeorder(Order order);
	public boolean cancelorder(String order_id);
}
