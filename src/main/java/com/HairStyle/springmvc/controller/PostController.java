package com.HairStyle.springmvc.controller;

import java.io.File;
import java.io.IOException;
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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.HairStyle.springmvc.model.Post_Pic;
import com.HairStyle.springmvc.model.Post_Type;
import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.PostServiceImpl;



@Controller
@RequestMapping(value="api")
public class PostController {
	
	@Resource
    private PostServiceImpl PostService;
	public String PicPath=ConfigPath.getConfigPath();
	
	//通过帖文类型帖文集
	@RequestMapping(value="getpostbytype",method=RequestMethod.GET)
    @ResponseBody
    public Post_Type get_postbytype(@RequestParam("post_type") String post_type,HttpServletRequest request) {
			
			String user_id=null;
			String user_name=null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
	              	if(cookie.getName().equals("app_user_info")){
	              		String loginInfo = cookie.getValue();
	              		user_id = loginInfo.substring(0,19);
	              		break;
	              	}
			}
			
			Map<String, Object> post_request = new HashMap<String, Object>();
			post_request.put("user_id", user_id);
			post_request.put("post_type", post_type);
			Post_Type post_all=PostService.getPostContentByType(post_request);		
						
			return post_all;
			

	}
	

	//通过用户获取帖文集
	@RequestMapping(value="getpostbyuserid",method=RequestMethod.GET)
    @ResponseBody
    public User get_postbyuser(@RequestParam("user_id") String user_id,HttpServletRequest request) {
			
			String userd_id=null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
              		if(cookie.getName().equals("app_user_info")){
              			String loginInfo = cookie.getValue();
              			userd_id = loginInfo.substring(0,19);
              			break;
              		}
			}
			Map<String, Object> post_request = new HashMap<String, Object>();
			post_request.put("user_id", user_id);
			post_request.put("userd_id", userd_id);
			User post_all=PostService.getPostContentByUser_id(post_request);
		
			return post_all;
	}
	
	//通过帖文id获得内容、评论、回复等
	@RequestMapping(value="getpostbypostid",method=RequestMethod.GET)
    @ResponseBody
    public Poster get_postbypostid(@RequestParam("post_id") String post_id,HttpServletRequest request) {
			
		String user_id=null;

		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
          		if(cookie.getName().equals("app_user_info")){
          			String loginInfo = cookie.getValue();
          			user_id = loginInfo.substring(0,19);
          			break;
          		}
		}
		
		Map<String, Object> post_request = new HashMap<String, Object>();
		post_request.put("user_id", user_id);
		post_request.put("post_id", post_id);
		Poster post_all=PostService.getPostByPost_id(post_request);
		
			return post_all;
			
	}
	
	//删除帖文
	@RequestMapping(value="deletepost",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> delete_post(@RequestParam("post_id") String post_id) {
			
			Map<String, Object> delete_post_state = new HashMap<String, Object>();
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    		String last_edit = sdf1.format(date);
    		Date last_edit_time = null;
			try {
				last_edit_time = sdf1.parse(last_edit);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, Object> delete_post = new HashMap<String, Object>();
			delete_post.put("post_id", post_id);
			delete_post.put("last_edit_time", last_edit_time);
			if(PostService.Delete_article(delete_post)){
				delete_post_state.put("msg", "删除成功！");
				delete_post_state.put("status", 0);
			}
			else{
				delete_post_state.put("msg", "删除失败！");
				delete_post_state.put("status", 1);
			}
			return delete_post_state;
	}
	
	//删除帖文图片
		 @RequestMapping(value="delete_post_pic",method=RequestMethod.POST)
		 @ResponseBody
		 public Map<String, Object> delete_post_pic(@RequestParam("idpost_pic") String idpost_pic, 
		    		HttpServletRequest request) {
			 Map<String, Object> delete_post_pic_state = new HashMap<String, Object>();		
			 Map<String, Object> mapforcancelpic = new HashMap<String, Object>();
			 mapforcancelpic.put("idpost_pic", idpost_pic);
			 if(PostService.delete_post_picture(mapforcancelpic)){
				 delete_post_pic_state.put("msg", "删除成功！");
			 	 delete_post_pic_state.put("status", 0);
			 	 }
			 else{
				 delete_post_pic_state.put("msg", "删除失败！");
			 	 delete_post_pic_state.put("status", 1);
			 }
		    	return delete_post_pic_state;
		    }
	
	
	 //修改帖文
	 @RequestMapping(value="modify_post",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> ModifyPost(@RequestParam(value="fileImg0",required = false) MultipartFile post_pic0,
	    		@RequestParam(value="fileImg1",required = false) MultipartFile post_pic1,
	    		@RequestParam(value="fileImg2",required = false) MultipartFile post_pic2,
	    		@RequestParam(value="fileImg3",required = false) MultipartFile post_pic3,
	    		@RequestParam(value="fileImg4",required = false) MultipartFile post_pic4,
	    		@RequestParam(value="fileImg5",required = false) MultipartFile post_pic5,
	    		@RequestParam(value="fileImg6",required = false) MultipartFile post_pic6,
	    		@RequestParam(value="fileImg7",required = false) MultipartFile post_pic7,
	    		@RequestParam(value="fileImg8",required = false) MultipartFile post_pic8,
	    		@RequestParam("post_id") String post_id,@RequestParam("post_type") String post_type, @RequestParam("post_content") String post_content, 
	    		HttpServletRequest request) {
	    		
	    		Map<String, Object> edit_post_state = new HashMap<String, Object>();
	    	
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String last_edit = sdf1.format(date);
	    		Date last_edit_time = null;
				try {
					last_edit_time = sdf1.parse(last_edit);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          
			    String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
			    
			    String pic_path=PicPath+"post";
			    String pic_path_user_post=pic_path+"/"+post_id;
				
			     MultipartFile[] imgs=new MultipartFile[9];
	             imgs[0]=post_pic0;
	             imgs[1]=post_pic1;
	             imgs[2]=post_pic2;
	             imgs[3]=post_pic3;
	             imgs[4]=post_pic4;
	             imgs[5]=post_pic5;
	             imgs[6]=post_pic6;
	             imgs[7]=post_pic7;
	             imgs[8]=post_pic8;
			    
				 try {				 	
				        for(int i=0;i<=8;i++){  
				        if(null != imgs[i] && null != imgs[i].getOriginalFilename()
				                && !"".equals(imgs[i].getOriginalFilename().trim())
				                && !"null".equals(imgs[i].getOriginalFilename().trim())){

				        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
				        				.format(new Date()).concat(imgs[i].getOriginalFilename());
				        		String filename = pic_path_user_post + File.separator +imagename;
				        		File file = new File(filename);				        		
				        		imgs[i].transferTo(file);//上传至服务器
			        			//将文件图片插入数据库
			        			Map<String, Object> mapforcancelpic = new HashMap<String, Object>();
			        			mapforcancelpic.put("seq_id",i);
			        			mapforcancelpic.put("pic_post_id",post_id);
			        			if(PostService.search_post_picture(mapforcancelpic)){
			        			PostService.delete_post_picture(mapforcancelpic);	
			        			}
			        			
			        			Map<String, Object> mapforpic = new HashMap<String, Object>();
			        			mapforpic.put("idpost_pic", "pic"+str+String.valueOf(i));
			        			mapforpic.put("seq_id",String.valueOf(i));
			        			mapforpic.put("post_pic_dir","/HairStyle/pic/picture/post/"+post_id+"/"+imagename);
			        			mapforpic.put("pic_post_id",post_id);
			        			PostService.insert_post_picture(mapforpic);				        	
				        }
				        else continue;
		            }
				        Map<String, Object> map = new HashMap<String, Object>();
			    		map.put("post_id", post_id);
			    		map.put("post_type", post_type);
			    		map.put("post_content", post_content);
			    		map.put("last_edit_time", last_edit_time);
			    		
			    		PostService.Update_article(map);
			    		edit_post_state.put("msg", "修改成功！");
			    		edit_post_state.put("status", 0);
			        } catch (IllegalStateException e) {
						e.printStackTrace();
						edit_post_state.put("msg", "修改失败！");
						edit_post_state.put("status", 1);
					} catch (IOException e) {
						e.printStackTrace();
						edit_post_state.put("msg", "输入有误！");
						edit_post_state.put("status", 2);
					}	
	    	
	    	return edit_post_state;
	    }
	 
	 //发布新帖文
	 	@RequestMapping(value="new_post",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> Post_article(@RequestParam(value="fileImg0",required = false) MultipartFile post_pic0,
	    		@RequestParam(value="fileImg1",required = false) MultipartFile post_pic1,
	    		@RequestParam(value="fileImg2",required = false) MultipartFile post_pic2,
	    		@RequestParam(value="fileImg3",required = false) MultipartFile post_pic3,
	    		@RequestParam(value="fileImg4",required = false) MultipartFile post_pic4,
	    		@RequestParam(value="fileImg5",required = false) MultipartFile post_pic5,
	    		@RequestParam(value="fileImg6",required = false) MultipartFile post_pic6,
	    		@RequestParam(value="fileImg7",required = false) MultipartFile post_pic7,
	    		@RequestParam(value="fileImg8",required = false) MultipartFile post_pic8,
	    		@RequestParam String post_type, @RequestParam String post_content, 
	    		HttpServletRequest request) {
	    	
	    		Map<String, Object> new_post_state = new HashMap<String, Object>();
	    	
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String create_time = sdf1.format(date);
	    		Date post_time = null;
				try {
					post_time = sdf1.parse(create_time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String user_id=null;

				Cookie[] cookies = request.getCookies();
			    for(Cookie cookie : cookies){
			              if(cookie.getName().equals("app_user_info")){
			            	  String loginInfo = cookie.getValue();
			                  user_id = loginInfo.substring(0,19);

			                  break;
			        }
			     }            
			    String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	    		String post_id="post"+str+rannum;
			    
			    String pic_path=PicPath+"post";
			    String pic_path_user_post=pic_path+"/"+post_id;
			    
			    File myPath = new File( pic_path_user_post );  
	            if ( !myPath.exists()){//若此目录不存在，则创建  
	                myPath.mkdir();   
	            }  
			    
	             MultipartFile[] imgs=new MultipartFile[9];
	             imgs[0]=post_pic0;
	             imgs[1]=post_pic1;
	             imgs[2]=post_pic2;
	             imgs[3]=post_pic3;
	             imgs[4]=post_pic4;
	             imgs[5]=post_pic5;
	             imgs[6]=post_pic6;
	             imgs[7]=post_pic7;
	             imgs[8]=post_pic8;
				 try {
					 	Map<String, Object> mapforpic = new HashMap<String, Object>();
				        Map<String, Object> map = new HashMap<String, Object>();
			    		map.put("post_id", post_id);
			    		map.put("post_user_id", user_id);
			    		map.put("post_type", post_type);
			    		map.put("post_content", post_content);
			    		map.put("post_time", post_time);
			    		PostService.Post_article(map);
			    		
			    		for(int i=0;i<=8;i++){  
					        
					        if(null != imgs[i] && null != imgs[i].getOriginalFilename()
					                && !"".equals(imgs[i].getOriginalFilename().trim())
					                && !"null".equals(imgs[i].getOriginalFilename().trim())){

					        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
					        				.format(new Date()).concat(imgs[i].getOriginalFilename());
					        		String filename = pic_path_user_post + File.separator +imagename;
					        		File file = new File(filename);				        		
					        		imgs[i].transferTo(file);//将图片保存下来
				        			//将文件图片插入数据库
				        			mapforpic.put("idpost_pic", "pic"+str+String.valueOf(i));
				        			mapforpic.put("seq_id",String.valueOf(i));
				        			mapforpic.put("post_pic_dir","/HairStyle/pic/picture/post/"+post_id+"/"+imagename);
				        			mapforpic.put("pic_post_id",post_id);
				        			PostService.insert_post_picture(mapforpic);				        	
					        }
					        else break;
			            }
			    		
			    		new_post_state.put("msg", "发布成功！");
			    		new_post_state.put("post_id", post_id);
			    		new_post_state.put("status", 0);
			        } catch (IllegalStateException e) {
						e.printStackTrace();
						new_post_state.put("msg", "发布失败！");
			    		new_post_state.put("status", 1);
					} catch (IOException e) {
						e.printStackTrace();
						new_post_state.put("msg", "输入有误！");
			    		new_post_state.put("status", 2);
					}	
	    	
	    	return new_post_state;
	    }
	
}