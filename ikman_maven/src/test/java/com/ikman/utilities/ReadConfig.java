package com.ikman.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
	File src = new File("./Configuration/config.properties");
	
	try{
		
		FileInputStream fis =new FileInputStream(src);
		pro = new Properties();
		pro.load(fis);
		}catch (Exception e){
		System.out.println("Exception is" +e.getMessage());
		
		}
	
	}
	
	public String getURL()
	{
		
		String url= pro.getProperty("url");
		return url;
	}
	
	public String get_username()
	{
		
		String uid= pro.getProperty("uid");
		return uid;
	}
	
	public String get_password()
	{
		
		String pw= pro.getProperty("pw");
		return pw;
	}
	
	public String get_chrompath()
	{
		
		String chromepath= pro.getProperty("chromepath");
		return chromepath;
	}
	
	
}


