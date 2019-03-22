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
import com.HairStyle.springmvc.model.User;
import com.HairStyle.springmvc.service.impl.PostServiceImpl;

@Controller
@RequestMapping(value="api")

public class GetPostController {
	
	@Resource
    private PostServiceImpl PostService;
	
	@RequestMapping(value="postcontent",method=RequestMethod.GET)
    @ResponseBody
    public List<Poster> register(@RequestParam String str,@RequestParam int i) {
			
			List<Poster> post_all=PostService.getPostContentByType(str,i);
		
			return post_all;
			

	}
}