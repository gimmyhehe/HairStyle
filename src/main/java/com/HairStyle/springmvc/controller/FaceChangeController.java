package com.HairStyle.springmvc.controller;

<<<<<<< HEAD:src/main/java/com/HairStyle/springmvc/controller/FaceChangeController.java
public class FaceChangeController {
=======
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

public class ModifyPostController {
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
    		@RequestParam String post_id,@RequestParam String post_type, @RequestParam String post_content, 
    		HttpServletRequest request) {
    	
    		Map<String, Object> new_post_state = new HashMap<String, Object>();
    	
    		Date date = new Date();
    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
    		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
    		String last_edit = sdf2.format(date);
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
		    
		    String pic_path="src/main/resources/picture/";
		    String pic_path_user_post=pic_path+"/"+post_id;
			
			 try {
				 	int i=0;
				 	Map<String, Object> mapforpic = new HashMap<String, Object>();
			        for(MultipartFile picFile : multipartfiles){  
	            	//MultipartFile picFile = multiRequest.getFile(iter.next());  
			        if(null != picFile && null != picFile.getOriginalFilename()
			                && !"".equals(picFile.getOriginalFilename().trim())
			                && !"null".equals(picFile.getOriginalFilename().trim())){
>>>>>>> 463aca1a58acb90118fd10d7d325fe8e6bbb56ca:src/main/java/com/HairStyle/springmvc/controller/ModifyPostController.java

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
		        			mapforpic.put("is_active",post_id);
		        			PostService.insert_post_picture(mapforpic);				        	
			        }
	            }
			        Map<String, Object> map = new HashMap<String, Object>();
		    		map.put("post_id", post_id);
		    		map.put("post_type", post_type);
		    		map.put("post_content", post_content);
		    		PostService.post_articleDao(map);
		    		new_post_state.put("msg", "修改成功！");
		    		new_post_state.put("status", 0);
		        } catch (IllegalStateException e) {
					e.printStackTrace();
					new_post_state.put("msg", "修改失败！");
		    		new_post_state.put("status", 1);
				} catch (IOException e) {
					e.printStackTrace();
					new_post_state.put("msg", "输入有误！");
		    		new_post_state.put("status", 2);
				}	
    	
    	return new_post_state;
    }
}
