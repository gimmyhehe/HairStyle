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

import com.HairStyle.springmvc.model.Common_Order;
import com.HairStyle.springmvc.model.Company;
import com.HairStyle.springmvc.model.Hairstyler;
import com.HairStyle.springmvc.model.Order;
import com.HairStyle.springmvc.model.Pic_Common_Order;
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;
import com.HairStyle.springmvc.model.Reply_Order;
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
	 
	 
	 @RequestMapping(value="ordersuccess",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> ordersuccess(@RequestParam("order_id") String order_id, 
	    		HttpServletRequest request) {
		 	Map<String,Object> order_state=new HashMap<String,Object>();		 
    		if(OrderService.ordersuccess(order_id)){
    			order_state.put("msg", "设置订单预约成功成功");
    			order_state.put("status", 0);
    		}
    		else{
    			order_state.put("msg", "设置订单预约成功失败");
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
	 	
	 	//评论订单
	 	@RequestMapping(value="commonorder",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> commonorder(@RequestParam(value="fileImg0",required = false) MultipartFile commont_pic0,
	    		@RequestParam(value="fileImg1",required = false) MultipartFile commont_pic1,
	    		@RequestParam(value="fileImg2",required = false) MultipartFile commont_pic2,
	    		@RequestParam(value="fileImg3",required = false) MultipartFile commont_pic3,
	    		@RequestParam(value="fileImg4",required = false) MultipartFile commont_pic4,
	    		@RequestParam(value="fileImg5",required = false) MultipartFile commont_pic5,
	    		@RequestParam(value="fileImg6",required = false) MultipartFile commont_pic6,
	    		@RequestParam(value="fileImg7",required = false) MultipartFile commont_pic7,
	    		@RequestParam(value="fileImg8",required = false) MultipartFile commont_pic8,
	    		@RequestParam String score, @RequestParam String commont_order_content,
	    		@RequestParam String commont_order_id,
	    		HttpServletRequest request) {
	    	
	    		Map<String, Object> new_commont_state = new HashMap<String, Object>();
	    	
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String create_time_temp = sdf1.format(date);
	    		Date create_time = null;
				try {
					create_time = sdf1.parse(create_time_temp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			    String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	    		String com_order_id=str+rannum;
			    
			    String pic_path=PicPath+"commont_order";
			    String pic_path_commont=pic_path+"/"+com_order_id;
			    
			    File myPath = new File( pic_path_commont );  
	            if ( !myPath.exists()){//若此目录不存在，则创建  
	                myPath.mkdir();   
	            }  
			    
	             MultipartFile[] imgs=new MultipartFile[9];
	             imgs[0]=commont_pic0;
	             imgs[1]=commont_pic1;
	             imgs[2]=commont_pic2;
	             imgs[3]=commont_pic3;
	             imgs[4]=commont_pic4;
	             imgs[5]=commont_pic5;
	             imgs[6]=commont_pic6;
	             imgs[7]=commont_pic7;
	             imgs[8]=commont_pic8;
				 try {
					 	Common_Order co=new Common_Order();
			    		co.setCom_order_id(com_order_id);
			    		co.setCommon_order_id(commont_order_id);
			    		co.setCommon_time(create_time);
			    		co.setCommon_order_content(commont_order_content);
			    		co.setScore(Integer.parseInt(score));
			    		
			    		OrderService.commonorder(co);
			    		Pic_Common_Order pco=new Pic_Common_Order();
			    		for(int i=0;i<=8;i++){  
					        
					        if(null != imgs[i] && null != imgs[i].getOriginalFilename()
					                && !"".equals(imgs[i].getOriginalFilename().trim())
					                && !"null".equals(imgs[i].getOriginalFilename().trim())){
					        		
					        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
					        				.format(new Date()).concat(imgs[i].getOriginalFilename());
					        		String filename = pic_path_commont + File.separator +imagename;
					        		File file = new File(filename);				        		
					        		imgs[i].transferTo(file);//将图片保存下来
				        			//将文件图片插入数据库
				        			pco.setp_c_o_common_id(com_order_id);
				        			pco.setP_c_o_seq_id(String.valueOf(i));
				        			pco.setP_c_o_id(str+String.valueOf(i));
				        			pco.setP_c_o_dir("/HairStyle/pic/picture/commont_order/"+com_order_id+"/"+imagename);				        			
				        			OrderService.insert_commonorder_pic(pco);				        	
					        }
					        else break;
			            }
			    		new_commont_state.put("msg", "发布评论成功！");
			    		new_commont_state.put("status", 0);
			        } catch (IllegalStateException e) {
						e.printStackTrace();
						new_commont_state.put("msg", "发布评论失败！");
			    		new_commont_state.put("status", 1);
					} catch (IOException e) {
						e.printStackTrace();
						new_commont_state.put("msg", "输入有误！");
			    		new_commont_state.put("status", 2);
					}	
	    	
	    	return new_commont_state;
	    }
	 	
	 	//商家回复用户对订单的评论
	 	@RequestMapping(value="replycommonorder",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> replycommonorder(
	    		@RequestParam String com_order_id, 
	    		@RequestParam String order_reply_content,
	    		HttpServletRequest request) {
	    	
	    		Map<String, Object> new_reply_state = new HashMap<String, Object>();
	    	
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String create_time_temp = sdf1.format(date);
	    		Date create_time = null;
				try {
					create_time = sdf1.parse(create_time_temp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			    String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	    		String order_reply_id=str+rannum;

				Reply_Order ro=new Reply_Order();
			    ro.setOrder_reply_content(order_reply_content);
			    ro.setorder_reply_time(create_time);
			    ro.setOrder_reply_id(order_reply_id);
			    ro.setReply_o_common_id(com_order_id);			    
			    		
			    if(OrderService.replycommonorder(ro)&&OrderService.setcommontreply(com_order_id)){		
			    new_reply_state.put("msg", "发布回复成功！");
			    new_reply_state.put("status", 0);
			    }
			    else{
			    	new_reply_state.put("msg", "发布回复失败！");
				    new_reply_state.put("status", 1);
			    }
			    return new_reply_state;
	    }
	 	
}
