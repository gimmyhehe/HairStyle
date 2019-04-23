package com.HairStyle.springmvc.service;

import java.util.Map;

import com.HairStyle.springmvc.model.Common_Order;
import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Order;
import com.HairStyle.springmvc.model.Pic_Common_Order;
import com.HairStyle.springmvc.model.Reply_Order;
import com.HairStyle.springmvc.model.User;

public interface IOrderService {
	public boolean completeorder(Map<String, Object> order_info);
	public boolean makeorder(Order order);
	public boolean ordersuccess(String order_id);
	public boolean cancelorder(String order_id);
	public User findorderbyuser_id(String user_id);
	public Company findorderbycompany_id(String company_id);
	public boolean insert_commonorder_pic(Pic_Common_Order pco);
	public boolean commonorder(Common_Order co);
	public boolean replycommonorder(Reply_Order ro);
}
