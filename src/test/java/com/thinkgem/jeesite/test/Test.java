package com.thinkgem.jeesite.test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.activiti.engine.impl.util.json.JSONObject;
import org.h2.command.ddl.CreateAggregate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.thinkgem.jeesite.modules.sys.entity.Dict;

public class Test {
	public static void main(String[] args) {
 ObjectMapper objectMapper = new ObjectMapper();
    	 
    	 //该特性决定序列化root级对象的实现closeable接口的close方法是否在序列化后被调用。
    	 SerializationFeature feature1 = SerializationFeature.CLOSE_CLOSEABLE;
    	 SerializationFeature feature2 = SerializationFeature.EAGER_SERIALIZER_FETCH;
    	 SerializationFeature feature3 = SerializationFeature.FAIL_ON_EMPTY_BEANS; 
    	 JSONObject data = new JSONObject();
    	 data.put("value1", "value222221");
    	 data.put("label1", "lable22");
    	 data.put("type1", "1");
    	 Dict dict = new Dict();
    	 dict.setValue("测试value");
    	 dict.setLabel("测试lavel");
//    	 Dict obj = json2Obj(data.toString(), Dict.class);
//    	 System.out.println(obj);
    	 String json = obj2Json(dict);
    	 System.out.println(json);
	}
	
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
}
