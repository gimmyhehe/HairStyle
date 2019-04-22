package com.HairStyle.springmvc.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;
import com.HairStyle.springmvc.model.Order;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.CompanyServiceImpl;
import com.HairStyle.springmvc.service.impl.OrderServiceImpl;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;


@Controller
@RequestMapping(value="api")
public class OrderController {

	@Resource
    private OrderServiceImpl OrderService;
	public String PicPath=ConfigPath.getConfigPath();
	
	
	 @RequestMapping(value="makeorder",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> makeorder(@RequestParam("product_id") String product_id, 
			 @RequestParam("product_amount") String product_amount,
			 @RequestParam("order_price") String order_price,
	    		HttpServletRequest request) {
		 	Map<String,Object> order_state=new HashMap<String,Object>();
		 	String user_id=null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
	          		if(cookie.getName().equals("user_info")){
	          			String loginInfo = cookie.getValue();
	          			user_id = loginInfo.substring(0,19);
	          			break;
	          		}
			}
		 
		 	Date date = new Date();
		 	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
		 	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
		 	String create_time_tmp = sdf1.format(date);
		 	Date create_time = null;
		 	try {
				create_time = sdf1.parse(create_time_tmp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		 	String str = sdf2.format(date);
    		Random random = new Random();	    		 
    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数    		    		
    		String order_id=str+rannum;
    		Order order=new Order();
    		order.setOrder_id(order_id);
    		order.setOrder_price(Integer.parseInt(order_price));
    		order.setOrder_product_id(Integer.parseInt(product_id));
    		order.setOrder_create_time(create_time);
    		order.setOrder_user_id(user_id);
    		
    		if(OrderService.makeorder(order)){
    			order_state.put("msg", "订单创建成功");
    			order_state.put("status", 0);
    		}
    		else{
    			order_state.put("msg", "订单创建失败");
    			order_state.put("status", 1);
    		}
    		return order_state;
	    }	
	 
	 @RequestMapping(value="completeorder",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> completeorder(@RequestParam("order_id") String order_id, 
	    		HttpServletRequest request) {
		 	Map<String,Object> order_state=new HashMap<String,Object>();

		 
		 	Date date = new Date();
		 	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
		 	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
		 	String finish_time_tmp = sdf1.format(date);
		 	Date finish_time = null;
		 	try {
		 		finish_time = sdf1.parse(finish_time_tmp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	Map<String,Object> order_info=new HashMap<String,Object>();
		 	order_info.put("order_id", order_id);
		 	order_info.put("order_finish_time", finish_time);
    		
    		if(OrderService.completeorder(order_info)){
    			order_state.put("msg", "设置订单完成成功");
    			order_state.put("status", 0);
    		}
    		else{
    			order_state.put("msg", "设置订单完成失败");
    			order_state.put("status", 1);
    		}
    		return order_state;
	    }	
	
	 
	 @RequestMapping(value="cancelorder",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> cancelorder(@RequestParam("order_id") String order_id, 
	    		HttpServletRequest request) {
		 	Map<String,Object> order_state=new HashMap<String,Object>();
	 	
    		
    		if(OrderService.cancelorder(order_id)){
    			order_state.put("msg", "设置订单取消成功");
    			order_state.put("status", 0);
    		}
    		else{
    			order_state.put("msg", "设置订单取消失败");
    			order_state.put("status", 1);
    		}
    		return order_state;
	    }	
	 
	//用户订单表
	 	@RequestMapping(value="findorderbyuser_id",method=RequestMethod.GET)
	    @ResponseBody
	    public User findorderbyuser_id(HttpServletRequest request) {
	 		String user_id=null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
	          		if(cookie.getName().equals("user_info")){
	          			String loginInfo = cookie.getValue();
	          			user_id = loginInfo.substring(0,19);
	          			break;
	          		}
			}																
				return OrderService.findorderbyuser_id(user_id);
		}
	 	
	 	//商家订单表
	 	@RequestMapping(value="findorderbycompany_id",method=RequestMethod.GET)
	    @ResponseBody
	    public Company findorderbycompany_id(HttpServletRequest request) {
	 		String company_id=null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
	          		if(cookie.getName().equals("user_info")){
	          			String loginInfo = cookie.getValue();
	          			company_id = loginInfo.substring(19,20);
	          			break;
	          		}
			}																
				return OrderService.findorderbycompany_id(company_id);
		}
}
