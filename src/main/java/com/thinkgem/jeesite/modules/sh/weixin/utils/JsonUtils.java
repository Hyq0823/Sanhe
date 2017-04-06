package com.thinkgem.jeesite.modules.sh.weixin.utils;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * json工具类
 * @author hyq
 *
 */
public class JsonUtils {

	/**
	 * 对象转换为json串
	 * 优雅的输出json,自动换行，缩进
	 * @param obj
	 * @return
	 */
	public static String obj2Json(Object obj){
		try {
		ObjectMapper objectMapper = new ObjectMapper();
			ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 对象到json串的转换，控制日期格式
	 * @param json
	 * @param clazz
	 * @author hyq c
	 * @return
	 */
     public static <T> T json2Obj(String json,Class<T> clazz){
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 try {
    		 objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
     }
     

     /**
      * json转换成map
      * @param json
      * @return
      * @throws Exception
      */
     @SuppressWarnings("unchecked")
     public static Map<String, Object> convertJsonToMap(String json) throws Exception {
         ObjectMapper objectMapper = new ObjectMapper();
         Map<String, Object> maps;
         try {
             maps = objectMapper.readValue(json, Map.class);
             return maps;
         } catch (JsonParseException e) {
             throw new Exception("您输入的参数错误");
         } catch (JsonMappingException e) {
             throw new Exception("您输入的参数错误");
         }
     }
}
