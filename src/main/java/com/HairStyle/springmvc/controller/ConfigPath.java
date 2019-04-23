package com.HairStyle.springmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;


public class ConfigPath {
		
	static String PicPath="D:\\HairStyle\\HairStyle\\src\\main\\resources\\picture\\";

	public static String getConfigPath() {						
	    return PicPath;
	}
	

}
