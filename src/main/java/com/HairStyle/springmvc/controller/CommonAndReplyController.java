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
import com.HairStyle.springmvc.service.impl.PostServiceImpl;
import com.HairStyle.springmvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value="api")
public class CommonAndReplyController {
	
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
	    @RequestMapping(value="makecommon",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> makeCommon(HttpServletRequest request) {
	    	String common_post_id=request.getParameter("common_post_id");
	    	String common_content=request.getParameter("common_content");
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
	        String str = sdf2.format(date);
	        Random random = new Random();	    		 
	        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数            
	        String common_id="com"+str+rannum;
	        
	        Common common =new Common();
			common.setCommon_id(common_id);
			common.setCommon_Post_id(common_post_id);
			common.setCommon_content(common_content);
			common.setCommon_time(create_time);
			common.setCommon_User_id(user_id);
			Map<String, Object> make_common_state = new HashMap<String, Object>();
			if(PostService.makecommon(common)){
				make_common_state.put("msg", "评论成功！");
				make_common_state.put("status", 0);

			}
			else{
				make_common_state.put("msg", "评论失败！");
				make_common_state.put("status", 1);
			}
			
			return make_common_state;
	    }
	    	
	    
	    
	    @RequestMapping(value="deletecommon",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> deleteCommon(@RequestParam String common_id,HttpServletRequest request) {
	    	Map<String, Object> delete_common_state = new HashMap<String, Object>();
	    	if(PostService.deletecommon(common_id)){
	    		delete_common_state.put("msg", "删除成功！");
	    		delete_common_state.put("status", 0);
			}
			else{
				delete_common_state.put("msg", "删除失败！");
				delete_common_state.put("status", 1);
			}
			
			return delete_common_state;
	    }
	    
	    
	    
	    
	    
	    
	    
	    @RequestMapping(value="makereply",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> makeReply(HttpServletRequest request) {
	    	String reply_common_id=request.getParameter("reply_common_id");
	    	String replyed_user_id=request.getParameter("replyed_user_id");
	    	String reply_content=request.getParameter("reply_content");
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
	        String str = sdf2.format(date);
	        Random random = new Random();	    		 
	        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数            
	        String reply_id="rep"+str+rannum;
	        
	        Reply reply =new Reply();
	        reply.setReply_id(reply_id);
	        reply.setReply_common_id(reply_common_id);
	        reply.setReply_user_id(user_id);
	        reply.setReplyed_user_id(replyed_user_id);
	        reply.setReply_content(reply_content);
	        reply.setReply_time(create_time);
			Map<String, Object> make_reply_state= new HashMap<String, Object>();
			if(PostService.makereply(reply)){
				make_reply_state.put("msg", "评论成功！");
				make_reply_state.put("status", 0);

			}
			else{
				make_reply_state.put("msg", "评论失败！");
				make_reply_state.put("status", 1);
			}
			
			return make_reply_state;
	    }
	    	
	    
	    
	    @RequestMapping(value="deletereply",method=RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object> deleteReply(@RequestParam String reply_id,HttpServletRequest request) {
	    	Map<String, Object> delete_reply_state = new HashMap<String, Object>();
	    	if(PostService.deletereply(reply_id)){
	    		delete_reply_state.put("msg", "删除成功！");
	    		delete_reply_state.put("status", 0);
			}
			else{
				delete_reply_state.put("msg", "删除失败！");
				delete_reply_state.put("status", 1);
			}
			
			return delete_reply_state;
	    }
	    
}
