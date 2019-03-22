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

import com.HairStyle.springmvc.model.Collection;
import com.HairStyle.springmvc.model.Common;
import com.HairStyle.springmvc.model.Reply;
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.model.like_table;
import com.HairStyle.springmvc.service.impl.PostServiceImpl;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value="api")
public class CollectController {
	
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
	    @RequestMapping(value="makecollect",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> makeCollect(HttpServletRequest request) {
	    	String collect_post_id=request.getParameter("collect_post_id");
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
	        Collection ct =new Collection();
	        ct.setCollect_post_id(collect_post_id);
	        ct.setCollector_id(user_id);
	        ct.setcollect_time(create_time);
	        
			Map<String, Object> make_collect_state = new HashMap<String, Object>();
			if(PostService.makecollect(ct)){
				make_collect_state.put("msg", "收藏成功！");
				make_collect_state.put("status", 0);

			}
			else{
				make_collect_state.put("msg", "收藏失败！");
				make_collect_state.put("status", 1);
			}
			
			return make_collect_state;
	    }
	    	
	    
	    
	    @RequestMapping(value="deletecollect",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> deleteCollect(@RequestParam String collect_id,HttpServletRequest request) {
	    	Map<String, Object> delete_collect_state = new HashMap<String, Object>();
	    	if(PostService.deletecollect(collect_id)){
	    		delete_collect_state.put("msg", "删除成功！");
	    		delete_collect_state.put("status", 0);
			}
			else{
				delete_collect_state.put("msg", "删除失败！");
				delete_collect_state.put("status", 1);
			}
			
			return delete_collect_state;
	    }
	    
	    
	    @RequestMapping(value="usercollect",method=RequestMethod.GET)
	    @ResponseBody
	    public User get_user_collect(@RequestParam("user_id") String user_id,HttpServletRequest request) {
				
				String userd_id=null;

				Cookie[] cookies = request.getCookies();
				for(Cookie cookie : cookies){
	              		if(cookie.getName().equals("user_info")){
	              			String loginInfo = cookie.getValue();
	              			userd_id = loginInfo.substring(0,19);

	              			break;
	              		}
				}
				Map<String, Object> post_request = new HashMap<String, Object>();
				post_request.put("user_id", user_id);
				post_request.put("userd_id", userd_id);
				User post_all=PostService.getuser_collectByUser_id(post_request);
			
				return post_all;
		}
  
}