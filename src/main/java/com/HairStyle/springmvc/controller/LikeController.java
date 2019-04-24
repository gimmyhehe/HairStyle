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

import com.HairStyle.springmvc.model.Common;
import com.HairStyle.springmvc.model.Reply;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.like_table;
import com.HairStyle.springmvc.service.impl.PostServiceImpl;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value="api")
public class LikeController {
	
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
	    @RequestMapping(value="makelike",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> makeLike(HttpServletRequest request) {
	    	String like_post_id=request.getParameter("like_post_id");
	    	String user_id=null;

	    	Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
	            if(cookie.getName().equals("app_user_info")){
	          	  String loginInfo = cookie.getValue();
	                user_id = loginInfo.substring(0,19);

	                break;
	      }
			}
			Date date = new Date();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	        String create_time_tmp = sdf1.format(date);
	        Date create_time = null;
	        try {
					create_time = sdf1.parse(create_time_tmp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	        
	        like_table lt =new like_table();
	        lt.setLike_post_id(like_post_id);
	        lt.setLike_user_id(user_id);
	        lt.setLike_time(create_time);
	        
			Map<String, Object> make_like_state = new HashMap<String, Object>();
			if(PostService.makelike(lt)){
				make_like_state.put("msg", "点赞成功！");
				make_like_state.put("status", 0);

			}
			else{
				make_like_state.put("msg", "点赞失败！");
				make_like_state.put("status", 1);
			}
			
			return make_like_state;
	    }
	    	
	    
	    
	    @RequestMapping(value="deletelike",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> deleteLike(@RequestParam String like_id,HttpServletRequest request) {
	    	Map<String, Object> delete_like_state = new HashMap<String, Object>();
	    	if(PostService.deletelike(like_id)){
	    		delete_like_state.put("msg", "删除成功！");
	    		delete_like_state.put("status", 0);
			}
			else{
				delete_like_state.put("msg", "删除失败！");
				delete_like_state.put("status", 1);
			}
			
			return delete_like_state;
	    }
	    
	    
		@RequestMapping(value="userlike",method=RequestMethod.GET)
	    @ResponseBody
	    public User get_user_like(@RequestParam("user_id") String user_id,HttpServletRequest request) {
				
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
				User post_all=PostService.getuser_likeByUser_id(post_request);
			
				return post_all;
		}
  
	    
	    
	    
}
