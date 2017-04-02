package com.thinkgem.jeesite.modules.sys.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
/**
 * 操作system.properties的工具类
 * @author c
 *
 */
public class SysUtils {
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	public static String getPropertyByName(String name){
		if(name==null || "".equals(name)) return null;
		
		Properties pt = new Properties();
		InputStream in = null;
		try {
			Resource resource = resourceLoader.getResource("jeesite.properties");
			in = resource.getInputStream();
			pt.load(in);
			return pt.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
