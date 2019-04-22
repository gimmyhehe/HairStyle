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
	private UserServiceImpl UserService;
	public String PicPath=ConfigPath.getConfigPath();
	
	//注册商家信息
	@RequestMapping(value="bussinessregi",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request,HttpServletRequest response) {
    	String user_name=request.getParameter("user_name");
    	String phone_area=request.getParameter("phone_area");
    	String phone_number=request.getParameter("phone_number");
    	String email=request.getParameter("email");
    	
    	String company_name=request.getParameter("company_name");
		String location=request.getParameter("location");
		String company_intr=request.getParameter("company_intr");
    	
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
		    
		    String pic_path=PicPath+"user";
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
    		
    		Company cp= new Company(); 	    			    		
    		cp.setCompany_user_id(user_id);
    		cp.setCompany_intr(company_intr);
    		cp.setLocation(location);
    		cp.setCompany_name(company_name);
    		CompanyService.regicompany(cp);
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
		        		register_state.put("loc", filename);
		        		int rannum_pic = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
	        			//将文件图片插入数据库
	        			mapforpic.put("pic_id", str+rannum_pic);
	        			mapforpic.put("uploader_id",user_id);
	        			mapforpic.put("pic_date",create_time);
	        			mapforpic.put("user_pic_dir","/HairStyle/pic/picture/user/"+user_id+"/"+imagename);
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
	
	
	//修改商家信息
		 @RequestMapping(value="businessmodify",method = RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> modifyCompany(HttpServletRequest request,HttpServletRequest response) {
		    	
		    	String user_id=null;
		    	String company_id=null;
				Cookie[] cookies = request.getCookies();
			    for(Cookie cookie : cookies){
			              if(cookie.getName().equals("user_info")){
			            	  String loginInfo = cookie.getValue();
			                  user_id = loginInfo.substring(0,19);
			                  company_id = loginInfo.substring(19,20);
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
		    	String company_name=request.getParameter("company_name");
	    		String location=request.getParameter("location");
	    		String company_intr=request.getParameter("company_intr");
		    	Date birth_date = null;
		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	try {
		    		birth_date=sdf.parse(request.getParameter("birth_date"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	Map<String, Object> modifyCompany_data_state = new HashMap<String,Object>();
		    	Map<String, String> phone=new HashMap<String, String>();
		    	Map<String, String> mail_map=new HashMap<String, String>();
		    	phone.put("phone_area", phone_area);
		    	phone.put("phone_number", phone_number);
		    	phone.put("user_id", user_id);
		    	mail_map.put("email", email);
		    	mail_map.put("user_id", user_id);
		    	
		    	if(phone_area==null||phone_number==null||email==null||face_type==null||gender==null||career==null){
		    		modifyCompany_data_state.put("msg", "信息未完善！");
		    		modifyCompany_data_state.put("status", 3);
		    	}
		    	else if (UserService.isPhoneExist(phone)) {
					modifyCompany_data_state.put("msg", "该手机已注册！");
					modifyCompany_data_state.put("status", 2);
				}
		    	else if (UserService.isEmailExist(mail_map)) {
		    		modifyCompany_data_state.put("msg", "该邮件已注册！");
		    		modifyCompany_data_state.put("status", 1);
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
					Company cp= new Company(); 	    			    		
		    		cp.setCompany_id(Integer.parseInt(company_id));
		    		cp.setCompany_intr(company_intr);
		    		cp.setLocation(location);
		    		cp.setCompany_name(company_name);
					if(UserService.modifyUser_dataByUserID(map)&&CompanyService.modifycompany(cp)){;
					modifyCompany_data_state.put("msg", "修改资料成功！");
					modifyCompany_data_state.put("status", 0);
					}
					else {
						modifyCompany_data_state.put("msg", "修改资料失败！");
						modifyCompany_data_state.put("status", 1);
					}
					
			}
		    	return modifyCompany_data_state;
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
			   
			    String pic_path=PicPath+"HairStyle";
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
			    
			    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

			    Date date = new Date();
	    		String create = sdf1.format(date);
	    		Date create_time = null;
				try {
					create_time = sdf1.parse(create);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    try {
			    		hairstyle_pic.transferTo(file);//将图片保存下来
		        					        	
				 
					 	Hairstyler hs=new Hairstyler();
					 	hs.setHairstyle_company_id(company_id);
					 	hs.setHairstyle_intr(hairstyle_intr);
					 	hs.setHairstyle_level(hairstyle_level);
					 	hs.setHairstyle_name(hairstyle_name);
					 	hs.setHairstyle_pic("/HairStyle/pic/picture/HairStyle/"+company_id+"/"+imagename);
					 	hs.setCreate_time(create_time);
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
					   
				String pic_path=PicPath+"HairStyle";
				String pic_path_company=pic_path+"/"+company_id;					    					       				
				String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
					        				.format(new Date()).concat(hairstyle_pic.getOriginalFilename());
				String filename = pic_path_company + File.separator +imagename;
				File file = new File(filename);				        		
				try {
					   hairstyle_pic.transferTo(file);//将图片保存下来
				       hs.setHairstyle_pic("/HairStyle/pic/picture/HairStyle/"+company_id+"/"+imagename);

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
		
		@RequestMapping(value="onehairstyle",method=RequestMethod.GET)
	    @ResponseBody
	    public Hairstyler getHairstyle(@RequestParam("hairstyle_id") String hairstyle_id) {
																				
				return CompanyService.getHairstyle(hairstyle_id);
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

			    
	    		String pic_path=PicPath+"product"+ File.separator+company_id;
			    
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
				        			pp.setProduct_pic_dir("/HairStyle/pic/picture/product/"+company_id+"/"+imagename);
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
		
		//删除商品图片
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
		
		 //修改商品
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
		    		
		    		
				    String pic_path=PicPath+"product"+ File.separator+company_id;
				    
					
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
				        			pp.setProduct_pic_dir("/HairStyle/pic/picture/product/"+company_id+"/"+imagename);
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
		 
		 	//查看商家下的商品
		 	@RequestMapping(value="scan_products",method=RequestMethod.GET)
		    @ResponseBody
		    public Company findproductbycom(@RequestParam("company_id") String company_id,HttpServletRequest request) {
					
					
					Company company_product=CompanyService.findproductbycom(company_id);
				
					return company_product;
			}
		 	
		 	//查看单个商品下联同订单信息
}
