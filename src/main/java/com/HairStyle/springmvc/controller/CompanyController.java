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
import com.HairStyle.springmvc.model.Product;
import com.HairStyle.springmvc.model.Product_Pic;
import com.HairStyle.springmvc.model.User;
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
			    			hairstyler_state.put("msgg", imagename);
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
		
		//修改发型师信息
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
					        company_id = loginInfo.substring(20,21);

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
						   	hairstyler_state.put("msgg", imagename);
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
		
		//删除发型师
		@RequestMapping(value="delhairstyle",method=RequestMethod.GET)
	    @ResponseBody
	    public Map<String,Object> deleteHairstyle(@RequestParam("hairstyle_id") String hairstyle_id) {
				
				Map<String, Object> delete_hairstyler_state = new HashMap<String, Object>();
				
				
				if(CompanyService.deleteHairstyle(hairstyle_id)){
					delete_hairstyler_state.put("msg", "删除成功！");
					delete_hairstyler_state.put("status", 0);
				}
				else{
					delete_hairstyler_state.put("msg", "删除失败！");
					delete_hairstyler_state.put("status", 1);
				}
				
				return delete_hairstyler_state;
		}
		
		@RequestMapping(value="scanhairstyle",method=RequestMethod.GET)
	    @ResponseBody
	    public Company getHairstylist(@RequestParam("company_id") String company_id) {
																				
				return CompanyService.getHairstylist(company_id);
		}
		
		@RequestMapping(value="addproduct",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> addproduct(@RequestParam(value="fileImg0",required = false) MultipartFile product_pic0,
	    		@RequestParam(value="fileImg1",required = false) MultipartFile product_pic1,
	    		@RequestParam(value="fileImg2",required = false) MultipartFile product_pic2,
	    		@RequestParam(value="fileImg3",required = false) MultipartFile product_pic3,
	    		@RequestParam(value="fileImg4",required = false) MultipartFile product_pic4,
	    		@RequestParam(value="fileImg5",required = false) MultipartFile product_pic5,
	    		@RequestParam(value="fileImg6",required = false) MultipartFile product_pic6,
	    		@RequestParam(value="fileImg7",required = false) MultipartFile product_pic7,
	    		@RequestParam(value="fileImg8",required = false) MultipartFile product_pic8,
	    		@RequestParam String product_name, @RequestParam String product_price, 
	    		@RequestParam String product_intr, @RequestParam String product_amount, 
	    		HttpServletRequest request) {
	    	
	    		Map<String, Object> new_product_state = new HashMap<String, Object>();
	    	
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String create_time = sdf1.format(date);
	    		Date product_time = null;
				try {
					product_time = sdf1.parse(create_time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String user_id=null;
				String company_id=null;
				Cookie[] cookies = request.getCookies();
			    for(Cookie cookie : cookies){
			              if(cookie.getName().equals("user_info")){
			            	  String loginInfo = cookie.getValue();
			                  user_id = loginInfo.substring(0,19);
			                  company_id=loginInfo.substring(20,21);
			                  break;
			        }
			     }            
			    String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

			    
			    String pic_path="d:/HairStyle/HairStyle/src/main/resources/picture/product";
			    
			    File myPath = new File( pic_path );  
	            if ( !myPath.exists()){//若此目录不存在，则创建  
	                myPath.mkdir();   
	            }  
			    
	             MultipartFile[] imgs=new MultipartFile[9];
	             imgs[0]=product_pic0;
	             imgs[1]=product_pic1;
	             imgs[2]=product_pic2;
	             imgs[3]=product_pic3;
	             imgs[4]=product_pic4;
	             imgs[5]=product_pic5;
	             imgs[6]=product_pic6;
	             imgs[7]=product_pic7;
	             imgs[8]=product_pic8;
				 try {
					 	
				        Product product=new Product();
				        Product_Pic pp=new Product_Pic();
			    		product.setProduct_company_id(Integer.parseInt(company_id));
			    		product.setProduct_name(product_name);
			    		product.setProduct_intr(product_intr);
			    		product.setProduct_amount(Integer.parseInt(product_amount));
			    		product.setProduct_price(Double.parseDouble(product_price));
			    		product.setProduct_time(product_time);
			    		CompanyService.addproduct(product);
			    		
			    		for(int i=0;i<=8;i++){  
					        
					        if(null != imgs[i] && null != imgs[i].getOriginalFilename()
					                && !"".equals(imgs[i].getOriginalFilename().trim())
					                && !"null".equals(imgs[i].getOriginalFilename().trim())){

					        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
					        				.format(new Date()).concat(imgs[i].getOriginalFilename());
					        		String filename = pic_path + File.separator +imagename;
					        		File file = new File(filename);				        		
					        		imgs[i].transferTo(file);//将图片保存下来
				        			//将文件图片插入数据库
			 	        			pp.setProduct_pic_id(str+String.valueOf(i));
				        			pp.setProduct_seq_id(i);
				        			pp.setProduct_pic_dir(imagename);
				        			pp.setbe_product_id(product.getProduct_id());
				        			CompanyService.addproduct_pic(pp);				        	
					        }
					        else break;
			            }	
			    		new_product_state.put("msg", "插入商品成功！");
			    		new_product_state.put("status", 0);
			        } catch (IllegalStateException e) {
						e.printStackTrace();
						new_product_state.put("msg", "插入商品失败！");
						new_product_state.put("status", 1);
					} catch (IOException e) {
						e.printStackTrace();
						new_product_state.put("msg", "输入有误！");
						new_product_state.put("status", 2);
					}	
	    	
	    	return new_product_state;
	    }
		
		//删除帖文图片
		 @RequestMapping(value="delete_product_pic",method=RequestMethod.POST)
		 @ResponseBody
		 public Map<String, Object> delete_product_pic(@RequestParam("product_pic_id") String product_pic_id, 
		    		HttpServletRequest request) {
			 Map<String, Object> delete_product_pic_state = new HashMap<String, Object>();		
			 Map<String, Object> mapforcancelpic = new HashMap<String, Object>();
			 mapforcancelpic.put("product_pic_id", product_pic_id);
			 if(CompanyService.deleteproduct_pic(mapforcancelpic)){
				 delete_product_pic_state.put("msg", "删除成功！");
				 delete_product_pic_state.put("status", 0);
			 	 }
			 else{
				 delete_product_pic_state.put("msg", "删除失败！");
				 delete_product_pic_state.put("status", 1);
			 }
		    	return delete_product_pic_state;
		    }
		
		
		 @RequestMapping(value="modify_product",method=RequestMethod.POST)
		 @ResponseBody
		 public Map<String, Object> ModifyProduct(@RequestParam(value="fileImg0",required = false) MultipartFile product_pic0,
		    		@RequestParam(value="fileImg1",required = false) MultipartFile product_pic1,
		    		@RequestParam(value="fileImg2",required = false) MultipartFile product_pic2,
		    		@RequestParam(value="fileImg3",required = false) MultipartFile product_pic3,
		    		@RequestParam(value="fileImg4",required = false) MultipartFile product_pic4,
		    		@RequestParam(value="fileImg5",required = false) MultipartFile product_pic5,
		    		@RequestParam(value="fileImg6",required = false) MultipartFile product_pic6,
		    		@RequestParam(value="fileImg7",required = false) MultipartFile product_pic7,
		    		@RequestParam(value="fileImg8",required = false) MultipartFile product_pic8,
		    		@RequestParam String product_id,
		    		@RequestParam String product_name, @RequestParam String product_price, 
		    		@RequestParam String product_intr, @RequestParam String product_amount, 
		    		HttpServletRequest request) {
		    		
		    		Map<String, Object> edit_product_state = new HashMap<String, Object>();
		    	
		    		Date date = new Date();
		    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
		          
				    String str = sdf2.format(date);
		    		Random random = new Random();	    		 
		    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
				    
				    String pic_path="d:/HairStyle/HairStyle/src/main/resources/picture/product";;

					
				     MultipartFile[] imgs=new MultipartFile[9];
		             imgs[0]=product_pic0;
		             imgs[1]=product_pic1;
		             imgs[2]=product_pic2;
		             imgs[3]=product_pic3;
		             imgs[4]=product_pic4;
		             imgs[5]=product_pic5;
		             imgs[6]=product_pic6;
		             imgs[7]=product_pic7;
		             imgs[8]=product_pic8;
				    
					 try {				 	
					        for(int i=0;i<=8;i++){  
					        if(null != imgs[i] && null != imgs[i].getOriginalFilename()
					                && !"".equals(imgs[i].getOriginalFilename().trim())
					                && !"null".equals(imgs[i].getOriginalFilename().trim())){

					        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
					        				.format(new Date()).concat(imgs[i].getOriginalFilename());
					        		String filename = pic_path + File.separator +imagename;
					        		File file = new File(filename);				        		
					        		imgs[i].transferTo(file);//上传至服务器
				        			//将文件图片插入数据库
				        			Map<String, Object> mapforcancelpic = new HashMap<String, Object>();
				        			mapforcancelpic.put("product_seq_id",i);
				        			mapforcancelpic.put("be_product_id",Integer.parseInt(product_id));
				        			if(CompanyService.search_product_pic(mapforcancelpic)){
				        				CompanyService.deleteproduct_pic(mapforcancelpic);	
				        			}
				        			
				        			Product_Pic pp=new Product_Pic();
				        			pp.setProduct_pic_id(str+String.valueOf(i));
				        			pp.setProduct_seq_id(i);
				        			pp.setProduct_pic_dir(imagename);
				        			pp.setbe_product_id(Integer.parseInt(product_id));
				        			CompanyService.addproduct_pic(pp);					        	
					        }
					        else continue;
			            }
					        Product product=new Product();
				    		product.setProduct_name(product_name);
				    		product.setProduct_intr(product_intr);
				    		product.setProduct_amount(Integer.parseInt(product_amount));
				    		product.setProduct_price(Double.parseDouble(product_price));
				    		product.setProduct_id(Integer.parseInt(product_id));					        				    						    		
				    		CompanyService.modifyproduct(product);
				    		edit_product_state.put("msg", "修改商品成功！");
				    		edit_product_state.put("status", 0);
				        } catch (IllegalStateException e) {
							e.printStackTrace();
							edit_product_state.put("msg", "修改商品失败！");
							edit_product_state.put("status", 1);
						} catch (IOException e) {
							e.printStackTrace();
							edit_product_state.put("msg", "输入有误！");
							edit_product_state.put("status", 2);
						}	
		    	
		    	return edit_product_state;
		    }
		 
		 
		 	@RequestMapping(value="scan_products",method=RequestMethod.GET)
		    @ResponseBody
		    public Company findproductbycom(@RequestParam("company_id") String company_id,HttpServletRequest request) {
					
					
					Company company_product=CompanyService.findproductbycom(company_id);
				
					return company_product;
			}
}
