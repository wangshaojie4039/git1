package cn.imexue.ec.web.web.param;

import java.io.IOException;

import cn.imexue.ec.common.util.StringUtil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 用于解析json的时候去除头尾空格
 * @author Administrator
 *
 */
public class StringTrimConver extends JsonDeserializer<String>{

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getText();
		if(!StringUtil.isBlank(value)){
			
			return value.trim();
		}else{
			return null;
		}
	}
	
}
