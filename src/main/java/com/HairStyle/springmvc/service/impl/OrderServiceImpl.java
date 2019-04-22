package com.HairStyle.springmvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.HairStyle.springmvc.dao.IOrderDao;
import com.HairStyle.springmvc.dao.IPostDao;
import com.HairStyle.springmvc.dao.IUserDao;
import com.HairStyle.springmvc.model.Collection;
import com.HairStyle.springmvc.model.Common;
import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Order;
import com.HairStyle.springmvc.model.Post_Type;
import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Reply;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.like_table;
import com.HairStyle.springmvc.service.IOrderService;
import com.HairStyle.springmvc.service.IPostService;
import com.HairStyle.springmvc.service.IUserService;


@Service
public class OrderServiceImpl implements IOrderService{

	@Resource
	private IOrderDao orderDao;
	

	public boolean makeorder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.makeorderDao(order);
	}


	public boolean completeorder(Map<String, Object> order_info) {
		// TODO Auto-generated method stub
		return orderDao.completeorderDao(order_info);
	}


	public boolean cancelorder(String order_id) {
		// TODO Auto-generated method stub
		return orderDao.cancelorderDao(order_id);
	}


	public User findorderbyuser_id(String user_id) {
		// TODO Auto-generated method stub
		return orderDao.findorderbyuser_idDao(user_id);
	}

	public Company findorderbycompany_id(String company_id) {
		// TODO Auto-generated method stub
		return orderDao.findorderbycompany_idDao(company_id);
	}

}
