package com.HairStyle.springmvc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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


@Controller
@RequestMapping(value="api")
public class ChangeHairController {
				
		public String PicPath=ConfigPath.getConfigPath();
		 //换发型
		 @RequestMapping(value="changehair",method=RequestMethod.POST)
		 @ResponseBody
		 public Map<String, Object> ModifyPost(@RequestParam(value="fileImg0",required = false) MultipartFile hair_pic0,
		    		@RequestParam(value="fileImg1",required = false) MultipartFile hair_pic1, 
		    		HttpServletRequest request) {
			 
			 Map<String, Object> HairPic_state = new HashMap<String, Object>();
			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    	 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");  
	    	 
	    	 Date date = new Date(); 
			 String str = sdf2.format(date);
	    	 Random random = new Random();	    		 
	    	 int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	    	 String filepackname=str+rannum;
			 String pic_path=PicPath+"HairChange";
			 String pic_path_Pack=pic_path+File.separator+filepackname;
			 
			 File myPath = new File( pic_path_Pack );  
	            if ( !myPath.exists()){//若此目录不存在，则创建  
	                myPath.mkdir();   
	            }  
			 
			 MultipartFile[] imgs=new MultipartFile[2];
             imgs[0]=hair_pic0;
             imgs[1]=hair_pic1;
             String[] arg1=new String[3];
             arg1[2]=pic_path_Pack;
             try {				 	
			        for(int i=0;i<=1;i++){  
			        if(null != imgs[i] && null != imgs[i].getOriginalFilename()
			                && !"".equals(imgs[i].getOriginalFilename().trim())
			                && !"null".equals(imgs[i].getOriginalFilename().trim())){

			        		String imagename = new SimpleDateFormat("yyyyMMddHHmmss")
			        				.format(new Date()).concat(imgs[i].getOriginalFilename());
			        		String filename = pic_path_Pack + File.separator +imagename;
			        		File file = new File(filename);	
			        		arg1[i]=filename;
			        		imgs[i].transferTo(file);//上传至服务器		
			        }
			        else{
			        	HairPic_state.put("msg", "请上传两张图片");
			        	HairPic_state.put("status", 1);
			        	break;
			        }
			        
			        }
			        try {
			        String[] args = new String[]{ "python3", PicPath+"change_face.py",arg1[0],arg1[1],arg1[2]};
			        Process proc = Runtime.getRuntime().exec(args);//执行py文件
			        proc.waitFor();
			        } catch (IOException e) {
			            e.printStackTrace();
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
		             HairPic_state.put("msg", "成功输出合成图");
		             HairPic_state.put("PicPath", "/HairStyle/pic/picture/HairChange/"+filepackname+"/output.jpg");
			         HairPic_state.put("status", 0);
			         
			         
             }catch (IllegalStateException e) {
					e.printStackTrace();
					HairPic_state.put("msg", "图片出错！");
					HairPic_state.put("status", 1);
				} catch (IOException e) {
					e.printStackTrace();
					HairPic_state.put("msg", "输入有误！");
					HairPic_state.put("status", 2);
				}
             return HairPic_state;
             
        
		 }             		 
}
