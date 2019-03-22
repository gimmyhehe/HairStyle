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
import com.HairStyle.springmvc.service.impl.CompanyServiceImpl;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;


@Controller
@RequestMapping(value="api")
public class CompanyController {
	
	@Resource
    private CompanyServiceImpl CompanyService;
	
	
	//注册商家信息
	@RequestMapping(value="bussinessregi",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request) {
    		String company_user_id=request.getParameter("user_id");
    		String company_name=request.getParameter("company_name");
    		String location=request.getParameter("location");
    		String company_intr=request.getParameter("company_intr");
    		Map<String, Object> register_state = new HashMap<String, Object>();    	    		
			Company cp= new Company(); 	    			    		
    		cp.setCompany_user_id(company_user_id);
    		cp.setCompany_intr(company_intr);
    		cp.setLocation(location);
    		cp.setCompany_name(company_name);
    		if(CompanyService.regicompany(cp)){   			    					   		    
    			register_state.put("msg", "注册成功！");
    			register_state.put("status", 0);
    		}
    		else{
    			register_state.put("msg", "注册失败！");
        		register_state.put("status", 1);
    		}
    
    	return register_state;
    }

	
	
	
	//修改商家信息
		@RequestMapping(value="businessmodify",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> modifyCompany(HttpServletRequest request) {
	    		String company_id=request.getParameter("company_id");
	    		String company_name=request.getParameter("company_name");
	    		String location=request.getParameter("location");
	    		String company_intr=request.getParameter("company_intr");
	    		Map<String, Object> register_state = new HashMap<String, Object>();    	    		
				Company cp= new Company(); 	    			    		
	    		cp.setCompany_id(Integer.parseInt(company_id));
	    		cp.setCompany_intr(company_intr);
	    		cp.setLocation(location);
	    		cp.setCompany_name(company_name);
	    		if(CompanyService.modifycompany(cp)){   			    					   		    
	    			register_state.put("msg", "修改成功！");
	    			register_state.put("status", 0);
	    		}
	    		else{
	    			register_state.put("msg", "修改失败！");
	        		register_state.put("status", 1);
	    		}
	    
	    	return register_state;
	    }
		
		//查看商家
		//查看附近商家
		
		//录入发型师信息
		@RequestMapping(value="addhairstyle",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> regiHairstyle(@RequestParam(value="fileImg") MultipartFile hairstyle_pic,
	    		@RequestParam String hairstyle_name, @RequestParam String hairstyle_intr,@RequestParam String hairstyle_level, 
	    		HttpServletRequest request) {
	    	
	    		Map<String, Object> hairstyler_state = new HashMap<String, Object>();
	    	
				String company_id=null;

				Cookie[] cookies = request.getCookies();
			    for(Cookie cookie : cookies){
			              if(cookie.getName().equals("user_info")){
			            	  String loginInfo = cookie.getValue();
			            	  company_id = loginInfo.substring(19,20);

			                  break;
			        }
			     }            
			   
			    String pic_path="d:/HairStyle/HairStyle/src/main/resources/picture/HairStyle";
			    String pic_path_company=pic_path+"/"+company_id;
			    
			    File myPath = new File( pic_path_company );  
	            if ( !myPath.exists()){//若此目录不存在，则创建  
	                myPath.mkdir();   
	            }  

			        
			    if(null != hairstyle_pic && null != hairstyle_pic.getOriginalFilename()
			       && !"".equals(hairstyle_pic.getOriginalFilename().trim())
			       && !"null".equals(hairstyle_pic.getOriginalFilename().trim())){

			    String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
			        				.format(new Date()).concat(hairstyle_pic.getOriginalFilename());
			    String filename = pic_path_company + File.separator +imagename;
			    File file = new File(filename);				        		
			    try {
			    		hairstyle_pic.transferTo(file);//将图片保存下来
		        					        	
				 
					 	Hairstyler hs=new Hairstyler();
					 	hs.setHairstyle_company_id(company_id);
					 	hs.setHairstyle_intr(hairstyle_intr);
					 	hs.setHairstyle_level(hairstyle_level);
					 	hs.setHairstyle_name(hairstyle_name);
					 	hs.setHairstyle_pic(imagename);
			    		if(CompanyService.regiHairstyle(hs)){			    		
			    			hairstyler_state.put("msg", "发布成功！");
			    			hairstyler_state.put("status", 0);
			    		}
			    		else{
			    			hairstyler_state.put("msg", "发布失败！");
							hairstyler_state.put("status", 1);
			    		}
			        } catch (IllegalStateException e) {
						hairstyler_state.put("msg", "发布失败！");
						hairstyler_state.put("status", 1);
					} catch (IOException e) {
						hairstyler_state.put("msg", "输入有误！");
						hairstyler_state.put("status", 2);
					}	
			    }else{
			    	hairstyler_state.put("msg", "请上传发型师图片！");
					hairstyler_state.put("status", 3);
			    }
	    	return hairstyler_state;
	    }
		
		//录入发型师信息
		@RequestMapping(value="modifyhairstyle",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> modifyHairstyle(@RequestParam(value="fileImg",required = false) MultipartFile hairstyle_pic,
				@RequestParam String hairstyle_id,
			    @RequestParam String hairstyle_name, @RequestParam String hairstyle_intr,@RequestParam String hairstyle_level, 
			    HttpServletRequest request) {
			    	
			    Map<String, Object> hairstyler_state = new HashMap<String, Object>();
			    Hairstyler hs=new Hairstyler();
			    hs.setHairstyle_id(Integer.parseInt(hairstyle_id));
				hs.setHairstyle_intr(hairstyle_intr);
				hs.setHairstyle_level(hairstyle_level);
				hs.setHairstyle_name(hairstyle_name);
			    if(null != hairstyle_pic && null != hairstyle_pic.getOriginalFilename()
						&& !"".equals(hairstyle_pic.getOriginalFilename().trim())
						&& !"null".equals(hairstyle_pic.getOriginalFilename().trim())){
				String company_id=null;

				Cookie[] cookies = request.getCookies();
				for(Cookie cookie : cookies){
					  if(cookie.getName().equals("user_info")){
						  	String loginInfo = cookie.getValue();
					        company_id = loginInfo.substring(19,20);

					        break;
					      }
					}            
					   
				String pic_path="d:/HairStyle/HairStyle/src/main/resources/picture/HairStyle";
				String pic_path_company=pic_path+"/"+company_id;					    					       				
				String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
					        				.format(new Date()).concat(hairstyle_pic.getOriginalFilename());
				String filename = pic_path_company + File.separator +imagename;
				File file = new File(filename);				        		
				try {
					   hairstyle_pic.transferTo(file);//将图片保存下来
				       hs.setHairstyle_pic(imagename);

					   if(CompanyService.modifyHairstyle(hs)){			    		
					    	hairstyler_state.put("msg", "修改成功！");
					    	hairstyler_state.put("status", 0);
					   }
					   else{
					    	hairstyler_state.put("msg", "修改失败！");
							hairstyler_state.put("status", 1);
					   }
					 } catch (IllegalStateException e) {
							hairstyler_state.put("msg", "修改失败！");
							hairstyler_state.put("status", 1);
					 } catch (IOException e) {
							hairstyler_state.put("msg", "输入有误！");
							hairstyler_state.put("status", 2);
					 }	
				}
			    else{

			    	if(CompanyService.modifyHairstyle(hs)){			    		
				    	hairstyler_state.put("msg", "修改成功！");
				    	hairstyler_state.put("status", 0);
				     }
			    	else{
				    	hairstyler_state.put("msg", "修改失败！");
						hairstyler_state.put("status", 1);
				     }	
					}
			    	return hairstyler_state;
			    }		
		
}
