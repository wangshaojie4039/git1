package cn.imexue.ec.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * httpclient工具类
 * 
 * @author hl
 *
 */
public class HttpClientUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doHttpGet(String url) {
		HttpGet httpget = new HttpGet(url);  
		 try {  
	            CloseableHttpClient httpClient = HttpClientFactory.getHttpClient();
	            CloseableHttpResponse response = httpClient.execute(httpget);
	            HttpEntity entity = response.getEntity();
	            return EntityUtils.toString(entity, "UTF-8");
	        } catch (Exception e) {
	            log.error("访问{}出错，信息:{}",url,e.getMessage());
	            return null;
	        }  
    }
	
	public static String doPost(String url,Map<String, String> maps){
		HttpPost httpPost = new HttpPost(url);// 创建httpPost    
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
            CloseableHttpClient httpClient = HttpClientFactory.getHttpClient();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            log.error("访问{}出错，信息:{}",url,e.getMessage());
            return null;
        }  
	}
	
	public static String doPost(String url,String json){
		HttpPost httpPost = new HttpPost(url);// 创建httpPost    
        // 创建参数队列    
        try {
            httpPost.setEntity(new StringEntity(json, "utf-8"));  
            CloseableHttpClient httpClient = HttpClientFactory.getHttpClient();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            log.error("访问{}出错，信息:{}",url,e.getMessage());
            return null;
        }
	}
	
	
}
