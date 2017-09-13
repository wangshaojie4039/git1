package cn.imexue.ec.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.imexue.ec.common.exception.AppChkException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObject {

	private static final Logger log = LoggerFactory.getLogger(JsonObject.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper(); 
	
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	public static String beanToJson(Object object){
		
		try {
			return getObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("转json错误:{}",e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String beanToJson(Collection list){
		return list.stream().map(x->{
			try {
				return getObjectMapper().writeValueAsString(x);
			} catch (JsonProcessingException e) {
				return null;
			}
		}).collect(Collectors.toList()).toString();
	}
	
	public static <T> List<T> jsonToBeanList(String json,Class<T> clazz){
		
		try {
			JavaType javaType = getCollectionType(ArrayList.class, clazz); 
			return getObjectMapper().readValue(json, javaType); 
		} catch (IOException e) {
			log.error("转bean错误:{}",e.getMessage());
			return null;
		}
	}
	
	public static <T> T jsonToBean(String json,Class<T> clazz){
		
		try {
			return getObjectMapper().readValue(json, clazz);
		} catch (IOException e) {
			log.error("转bean错误:{}",e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json){
		return jsonToBean(json, Map.class);
	}
	
	public static JsonNode getJson(String json){
		try {
			return objectMapper.readTree(json);
		} catch (IOException e) {
			throw new AppChkException(1008,"system.web.json.err");
		}
	}
	
	
	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		return getObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}  
}
