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

import com.HairStyle.springmvc.model.Poster;
import com.HairStyle.springmvc.service.impl.PostServiceImpl;

@Controller
@RequestMapping(value="api")
public class Post_articleController {
	
	 @Resource
	    private PostServiceImpl PostService;

	    /**
	     * 
	     * 
	     * @param 
	     * @param 
	     * @return
	     * @throws 
	     */
	    @RequestMapping(value="new_post",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> register(@RequestParam("fileImg") MultipartFile[] multipartfiles,
	    		@RequestParam String post_type, @RequestParam String post_content, 
	    		HttpServletRequest request) {
	    	
	    		Map<String, Object> new_post_state = new HashMap<String, Object>();
	    	
	    		Date date = new Date();
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
	    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    		String create_time = sdf2.format(date);
	    		Date post_time = null;
				try {
					post_time = sdf1.parse(create_time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String user_id=null;
				String user_name=null;
				Cookie[] cookies = request.getCookies();
			    for(Cookie cookie : cookies){
			              if(cookie.getName().equals("user_info")){
			            	  String loginInfo = cookie.getValue();
			                  user_id = loginInfo.substring(0,19);
			                  user_name = loginInfo.substring(19,loginInfo.length());
			                  break;
			        }
			     }            
			    String str = sdf2.format(date);
	    		Random random = new Random();	    		 
	    		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	    		String post_id="post"+str+rannum;
			    
			    String pic_path="src/main/resources/picture/";
			    String pic_path_user_post=pic_path+"/"+post_id;
			    
			    File logoSaveFile = new File(pic_path_user_post);
				if (!logoSaveFile.exists()){
					logoSaveFile.mkdirs();
				}
				
				 try {
					 	int i=0;
					 	Map<String, Object> mapforpic = new HashMap<String, Object>();
				        for(MultipartFile picFile : multipartfiles){  
		            	//MultipartFile picFile = multiRequest.getFile(iter.next());  
				        if(null != picFile && null != picFile.getOriginalFilename()
				                && !"".equals(picFile.getOriginalFilename().trim())
				                && !"null".equals(picFile.getOriginalFilename().trim())){

				        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
				        				.format(new Date()).concat(picFile.getOriginalFilename());
				        		String filename = pic_path_user_post + File.separator +imagename;
				        		File file = new File(filename);				        		
			        			picFile.transferTo(file);//上传至服务器
			        			//将文件图片插入数据库
			        			mapforpic.put("id_post_id", "pic"+str+String.valueOf(i));
			        			mapforpic.put("seq_id",String.valueOf(i));
			        			mapforpic.put("post_pic_dir",imagename);
			        			mapforpic.put("pic_post_id",post_id);
			        			PostService.insert_post_picture(mapforpic);				        	
				        }
		            }
				        Map<String, Object> map = new HashMap<String, Object>();
			    		map.put("post_id", post_id);
			    		map.put("post_user_id", user_id);
			    		map.put("post_type", post_type);
			    		map.put("post_content", post_content);
			    		map.put("post_time", post_time);
			    		PostService.post_articleDao(map);
			    		new_post_state.put("msg", "发布成功！");
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
