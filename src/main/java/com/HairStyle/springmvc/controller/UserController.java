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
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.User_Pic;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value="api")
public class UserController {
	
	 @Resource
	    private UserServiceImpl UserService;

	    /**
	     * 
	     * 
	     * @param 
	     * @param 
	     * @return
	     * @throws 
	     */
	 	//注册
	    @RequestMapping(value="register",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> register(HttpServletRequest request,HttpServletRequest response) {
	    	String user_name=request.getParameter("user_name");
	    	String phone_area=request.getParameter("phone_area");
	    	String phone_number=request.getParameter("phone_number");
	    	String email=request.getParameter("email");
	    	Map<String, Object> register_state = new HashMap<String, Object>();
	    	Map<String, String> phone_map=new HashMap<String, String>();
	    	Map<String, String> email_map=new HashMap<String, String>();
	    	phone_map.put("phone_area", phone_area);
	    	phone_map.put("phone_number", phone_number);
	    	phone_map.put("user_name", user_name);
	    	email_map.put("email", email);
	    	email_map.put("user_name", user_name);
	    	if (UserService.isUserExist(user_name)) {
	    		register_state.put("msg", "用户名已存在！");
	    		register_state.put("status", 3);
	    	}
	    	else if (UserService.isEmailExist(email_map)) {
	    		register_state.put("msg", "该邮件已注册！");
	    		register_state.put("status", 4);

			}
	    	else if (UserService.isPhoneExist(phone_map)) {
	    		register_state.put("msg", "该手机已注册！");
	    		register_state.put("status", 5);

			}
	    	else{
	    		
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
	    		
	    		
	    		String user_id=str+rannum;
			    
			    String pic_path="d:/HairStyle/HairStyle/src/main/resources/picture/user";
			    String pic_path_user_head=pic_path+File.separator+user_id;
			    
			    File myPath = new File( pic_path_user_head );  
	            if ( !myPath.exists()){//若此目录不存在，则创建之  
	                myPath.mkdirs();   
	            }  
	            MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
	            MultipartFile user_img = multipartRequest.getFile("fileImg");

				 	    			    		
	    		Map<String, Object> map = new HashMap<String, Object>();
	    		map.put("user_id", user_id);
	    		map.put("user_name", user_name);
	    		map.put("password", request.getParameter("password"));
	    		map.put("phone_area", phone_area);
	    		map.put("phone_number", phone_number);
	    		map.put("email", email);
	    		map.put("gender", request.getParameter("gender"));
	    		map.put("birth_date", request.getParameter("birth_date"));
	    		map.put("face_type", request.getParameter("face_type"));
	    		map.put("career", request.getParameter("career"));
	    		map.put("create_time", create_time);
				map.put("country", request.getParameter("country"));
				map.put("province", request.getParameter("province"));
				map.put("area", request.getParameter("area"));
				map.put("user_type", request.getParameter("user_type"));
	    		UserService.register(map);
	    		
	    		try {
				 	Map<String, Object> mapforpic = new HashMap<String, Object>(); 
			        if(null != user_img && null != user_img.getOriginalFilename()
			                && !"".equals(user_img.getOriginalFilename().trim())
			                && !"null".equals(user_img.getOriginalFilename().trim())){

			        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
			        				.format(new Date()).concat(user_img.getOriginalFilename());
			        		String filename = pic_path_user_head + File.separator +imagename;
			        		File file = new File(filename);		
			        		if ( !file.exists()){//若此目录不存在，则创建 
			        			file.createNewFile();   
				            }  
			        		user_img.transferTo(file);//上传至服务器
			        		
			        		int rannum_pic = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		        			//将文件图片插入数据库
		        			mapforpic.put("pic_id", "pic"+str+rannum_pic);
		        			mapforpic.put("uploader_id",user_id);
		        			mapforpic.put("pic_date",create_time);
		        			mapforpic.put("user_pic_dir",imagename);
		        			UserService.upload_new_user_pic(mapforpic);				        					        
	            }	    			    			    					   
			    
	    		register_state.put("msg", "注册成功！");
	    		register_state.put("user_id", user_id);
	    		register_state.put("status", 0);		
	    	} catch (IllegalStateException e) {
				register_state.put("msg", "注册失败！");
				register_state.put("status", 1);
			} catch (IOException e) {
				register_state.put("msg", "输入信息有误！");
				register_state.put("status", 2);
				
			}	
	    	
	    }
	    	return register_state;
	    }
	    
	    
	    //登陆
	    @RequestMapping(value="login" , method = RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> login(User user,HttpServletResponse response) {
	    	String user_name=user.getUser_name();
	    	String password=user.getPassword();
	    	Map<String, Object> loginstate = new HashMap<String, Object>(); 
	    	if (user_name!=null||password!=null){
	    		Map<String, String> map = new HashMap<String, String>();   		
	    		  
	    		map.put("user_name", user_name);
	    		map.put("password", password);
	    		User user1 = UserService.login(map);
	    		if (user1 != null) {//登录成功进入首页
	    			String user_id=user1.getUser_id();
	    			int user_type=user1.getUser_type();
	    			String user_info=null;
	    			if(user_type==1){
	    				int company_id=user1.getCompany().getCompany_id();
	    				user_info=user_id+String.valueOf(user_type)+String.valueOf(company_id);
	    			}
	    			else{
	    				int company_id=0;
	    				user_info=user_id+String.valueOf(user_type)+String.valueOf(company_id);
	    			}
	    			User_Pic user_pic=user1.getUser_Pic();

	    			Cookie cookie1 = new Cookie("user_info",user_info);
	    			cookie1.setMaxAge(60*60);
	                cookie1.setPath("/HairStyle");
	                response.addCookie(cookie1);
	                response.addHeader("user_info",user_info);

	                loginstate.put("status", 0);
	                loginstate.put("user_type",user_type);
	                loginstate.put("user_pic",user_pic.getUser_pic_dir());
	                loginstate.put("msg", "登陆成功！");
	    			
	    		} else {
	    			loginstate.put("status", 1);
	                loginstate.put("msg", "用户名不存在或密码错误");
	                
	    		}
	    	}else{
	    		loginstate.put("status", 2);
	    		loginstate.put("msg", "用户名或密码不为空。");

	    	}
	    	return loginstate;
	    }
	    
	    
	    //登出
	    @RequestMapping(value="logout" , method = RequestMethod.GET)
	    @ResponseBody
	    public Map<String, String> logout(HttpServletRequest req,HttpServletResponse response){
	        Cookie[] cookies = req.getCookies();
	        Map<String, String> logoutstate = new HashMap<String, String>(); 
	        for (Cookie cookie :cookies){//遍历所有Cookie
	            if(cookie.getName().equals("user_info")){//找到对应的cookie
	            	cookie.setValue(null);
	                cookie.setMaxAge(0);//Cookie并不能根本意义上删除，只需要这样设置为0
	                cookie.setPath("/");//
	                response.addCookie(cookie);//重新响应                
	                logoutstate.put("status", "0");
	        		logoutstate.put("msg", "成功注销");       		
	            }
	        }
			return logoutstate;
	    }
	    
	    //修改用户资料
	    @RequestMapping(value="modifyUser_data",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> modifyUser_data(HttpServletRequest request,HttpServletRequest response) {
	    	
	    	String user_id=null;
			Cookie[] cookies = request.getCookies();
		    for(Cookie cookie : cookies){
		              if(cookie.getName().equals("user_info")){
		            	  String loginInfo = cookie.getValue();
		                  user_id = loginInfo.substring(0,19);
		                  break;
		        }
		     }      
	    	
			String phone_area=request.getParameter("phone_area");
	    	String phone_number=request.getParameter("phone_number");
	    	String email=request.getParameter("email");
	    	String face_type=request.getParameter("face_type");
	    	String gender=request.getParameter("gender");
	    	String career=request.getParameter("career");
	    	String country=request.getParameter("country");
	    	String province=request.getParameter("province");
	    	String area=request.getParameter("area");
	    	
	    	Date birth_date = null;
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	try {
	    		birth_date=sdf.parse(request.getParameter("birth_date"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	Map<String, Object> modifyUser_data_state = new HashMap<String,Object>();
	    	Map<String, String> phone=new HashMap<String, String>();
	    	Map<String, String> mail_map=new HashMap<String, String>();
	    	phone.put("phone_area", phone_area);
	    	phone.put("phone_number", phone_number);
	    	phone.put("user_id", user_id);
	    	mail_map.put("email", email);
	    	mail_map.put("user_id", user_id);
	    	
	    	if(phone_area==null||phone_number==null||email==null||face_type==null||gender==null||career==null){
	    		modifyUser_data_state.put("msg", "信息未完善！");
	    		modifyUser_data_state.put("status", 3);
	    	}
	    	else if (UserService.isPhoneExist(phone)) {
				modifyUser_data_state.put("msg", "该手机已注册！");
				modifyUser_data_state.put("status", 2);
			}
	    	else if (UserService.isEmailExist(mail_map)) {
	    		modifyUser_data_state.put("msg", "该邮件已注册！");
	    		modifyUser_data_state.put("status", 1);
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id",user_id);
				map.put("phone_area",phone_area);
				map.put("phone_number", phone_number);
				map.put("email", email);
				map.put("gender", gender);
				map.put("birth_date", birth_date);
				map.put("face_type", face_type);
				map.put("career", career);
				map.put("country", country);
				map.put("province", province);
				map.put("area", area);
				UserService.modifyUser_dataByUserID(map);
				modifyUser_data_state.put("msg", "修改资料成功！");
				modifyUser_data_state.put("status", 0);
				
		}
	    	return modifyUser_data_state;
		}

		//修改头像
		@RequestMapping(value="modifyUser_dataHeadPic",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> modifyUser_Head_Pic(@RequestParam("fileImg") MultipartFile multipartfiles,
				HttpServletRequest request) {
			Map<String, Object> modifyUser_dataHeadPicState=new HashMap<String,Object>();
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
		    
	        String pic_path="d:/HairStyle/HairStyle/src/main/resources/picture/user";
	        String pic_path_user_head=pic_path+File.separator+user_id;
		    
		    try {
			 	int i=0;
			 	Map<String, Object> mapforpic = new HashMap<String, Object>(); 
		        if(null != multipartfiles && null != multipartfiles.getOriginalFilename()
		                && !"".equals(multipartfiles.getOriginalFilename().trim())
		                && !"null".equals(multipartfiles.getOriginalFilename().trim())){

		        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
		        				.format(new Date()).concat(multipartfiles.getOriginalFilename());
		        		String filename = pic_path_user_head + File.separator +imagename;
		        		File file = new File(filename);	
		        		if ( !file.exists()){//若此目录不存在，则创建 
		        			file.createNewFile();   
			            }  
		        		multipartfiles.transferTo(file);//上传至服务器
		        		int rannum_pic = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
	        			//将文件图片插入数据库
	        			mapforpic.put("pic_id", "pic"+str+rannum_pic);
	        			mapforpic.put("uploader_id",user_id);
	        			mapforpic.put("pic_date",create_time);
	        			mapforpic.put("user_pic_dir",imagename);
	        			
	        			UserService.set_user_old_pic(user_id);
	        			
	        			UserService.upload_new_user_pic(mapforpic);		
	        			modifyUser_dataHeadPicState.put("msg", "修改头像成功！");
	        			modifyUser_dataHeadPicState.put("status", 0);
		        	}
		        }
		        catch (IOException e) {
				modifyUser_dataHeadPicState.put("msg", "输入有误！");
				modifyUser_dataHeadPicState.put("status", 1);
			}	
	        
			return modifyUser_dataHeadPicState;        
		}
		
		//修改密码
		@RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> modifyPassword(String user_name,String password,
				String new_password) {
			User user = new User();
	        user.setUser_name(user_name);
	        user.setPassword(password);
	        user.setNew_Password(new_password);
			Map<String, Object> modifyPassword_state = new HashMap<String, Object>();
			Map<String, String> map = new HashMap<String, String>();
			Map<String, String> map2 = new HashMap<String, String>();
			if(password!=null&&new_password!=null){
				map2.put("user_name", user.getUser_name());
				map2.put("password",user.getNew_Password());
				map.put("user_name", user.getUser_name());
				map.put("password", user.getPassword());
				User user1 = UserService.login(map);
					if (user1 != null) {
						UserService.modifyPasswordByUsername(map2);
						modifyPassword_state.put("msg", "修改密码成功！");
						modifyPassword_state.put("status", 0);
		
					} else {
						modifyPassword_state.put("msg", "原密码错误！");
						modifyPassword_state.put("status", 1);
					}
			}else{
				modifyPassword_state.put("msg", "原密码或新密码不能为空！");
				modifyPassword_state.put("status", 2);

			}
				return modifyPassword_state;
		}
		
		
		//获取用户资料
		@RequestMapping(value = "userdata", method = RequestMethod.GET)
		@ResponseBody		
		public User initUser_data(@RequestParam("user_name") String user_name) {
			User user=UserService.get_user_data(user_name);
			return user;
		}
}
