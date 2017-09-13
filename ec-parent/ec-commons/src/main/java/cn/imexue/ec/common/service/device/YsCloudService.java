package cn.imexue.ec.common.service.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.imexue.ec.common.dao.redis.KeyValueDAO;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.model.bo.YsDeviceBO;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.HttpUtil;
import cn.imexue.ec.common.util.JsonObject;
import cn.imexue.ec.common.util.RedisKeyConstant;
import cn.imexue.ec.common.util.StringUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 萤石云服务接口
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年3月4日
 * @author lijianfeng
 * @version 1.0
 */
@Service
public class YsCloudService {
	private static final Logger log = LoggerFactory.getLogger(YsCloudService.class);
	
//	/** 萤石云重新请求token的时间，萤石云返回的超时时间与当前时间的差如果在这个值范围内，则将重新请求，单位：秒 */
//	private final int tokenRequestLimit = 30;
	
	@Value("${ys.app.key}")
	private String ysAppKey;
	
	@Value("${ys.app.secret}")
	private String ysAppSecret;
	
	@Resource
	protected KeyValueDAO keyValueDAO;
	
	public String getAccessToken() {
		String accessKey = keyValueDAO.get(RedisKeyConstant.YS_TOKEN_REDIS_KEY);
		
		
		if (accessKey == null) {
			return createAccessToken(ysAppKey, ysAppSecret);
		}
		return accessKey;
	}
	
	/**
	 * 创建萤石云token
	 * 
	 * @return 萤石云token
	 * @throws AppChkException
	 */
	@SuppressWarnings("unchecked")
	private String createAccessToken(String key, String secret) {
		Map<String,String> data = new HashMap<String,String>();
		
		data.put("appKey",  key);
		data.put("appSecret", secret);
		String result = HttpUtil.post(Constants.YS_ACCESS_TOKEN_URL, "UTF-8", data);
		
		try {
			Map<String, Object> map = JsonObject.jsonToMap(result);
			String code = (String) map.get("code");
			if ("200".equals(code)) {
				Map<String, Object> jsonData = (Map<String, Object>) map.get("data");
				String accessToken = (String) jsonData.get("accessToken");
				long expireTime = (long) jsonData.get("expireTime");
				
				keyValueDAO.set(RedisKeyConstant.YS_TOKEN_REDIS_KEY, accessToken,expireTime);
				
				return accessToken;
			}
		} catch(Throwable e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public List<YsDeviceBO> getDeviceList(int pageStart, int pageSize) {
		List<YsDeviceBO> list = new ArrayList<YsDeviceBO>();
		Map<String,String> data = new HashMap<String,String>();
		data.put("accessToken", getAccessToken());
		data.put("pageStart", String.valueOf(pageStart));
		data.put("pageSize", String.valueOf(pageSize));
		String result = HttpUtil.post(Constants.YS_DEVICE_LIST_URL, "UTF-8", data);
		
		try {
			ObjectMapper objectMapper = JsonObject.getObjectMapper();
			JsonNode node = objectMapper.readTree(result);
			
			String code = node.get("code").asText();
			if ("200".equals(code)) {
				JsonNode jsonArray = node.get("data");
				for (JsonNode jsonObj : jsonArray) {
					YsDeviceBO bo = new YsDeviceBO();
					bo.setDeviceSerial(jsonObj.get("deviceSerial").asText());
					bo.setDeviceName(jsonObj.get("deviceName").asText());
					bo.setDeviceType(jsonObj.get("deviceType").asText());
					bo.setStatus(jsonObj.get("status").asInt());
					bo.setDefence(jsonObj.get("defence").asInt());
					bo.setDeviceVersion(jsonObj.get("deviceVersion").asText());
					list.add(bo);
				}
			}
		} catch(Throwable e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
	public YsDeviceBO getDevice(String deviceSerial) {
		YsDeviceBO ysDeviceBO = new YsDeviceBO();
		Map<String,String> data = new HashMap<String,String>();
		data.put("accessToken", getAccessToken());
		
		data.put("deviceSerial", deviceSerial);
		String result = HttpUtil.post(Constants.YS_DEVICE_INFO_URL, "UTF-8", data);
		
		JsonNode node = JsonObject.getJson(result);
		
		String code = node.get("code").asText();
		if ("200".equals(code)) {
			JsonNode jsonObj = node.get("data");
			ysDeviceBO.setDeviceSerial(jsonObj.get("deviceSerial").asText());
			ysDeviceBO.setDeviceName(jsonObj.get("deviceName").asText());
			ysDeviceBO.setDeviceType(jsonObj.get("deviceType").asText());
			ysDeviceBO.setStatus(jsonObj.get("status").asInt());
			ysDeviceBO.setDefence(jsonObj.get("defence").asInt());
			
		}
		return ysDeviceBO;
	}
	
	/**
	 * 设备注册
	 * @param deviceSerial 设备序列号
	 * @param validateCode 设备验证码，设备机身上的六位大写字母
	 * @throws AppChkException
	 */
	public void addDevice(String deviceSerial,String validateCode)throws AppChkException {

		Map<String,String> data = new HashMap<String,String>();
		data.put("accessToken", getAccessToken());
		data.put("deviceSerial", deviceSerial);
		data.put("validateCode", validateCode);
		String result = HttpUtil.post(Constants.YS_DEVICE_ADD_URL, "UTF-8", data);
		JsonNode node = JsonObject.getJson(result);
		String code = node.get("code").asText();
		if ("20002".equals(code)) {
			throw new AppChkException(1006,"ys.err","设备不存在");
		}
		if ("20007".equals(code)) {
			throw new AppChkException(1006,"ys.err","设备不在线");
		}
		if ("20010".equals(code)) {
			throw new AppChkException(1006,"ys.err","设备验证码错误");
		}
		if ("20011".equals(code)) {
			throw new AppChkException(1006,"ys.err","设备添加失败");
		}
		if ("20013".equals(code)) {
			throw new AppChkException(1006,"ys.err","设备已被别人添加");
		}
		if ("20014".equals(code)) {
			throw new AppChkException(1006,"ys.err","设备序列号不合法");
		}
		if ("49999".equals(code)) {
			throw new AppChkException(1006,"ys.err","数据异常");
		}
			
	}
	/**
	 * 获取直播地址
	 * @param deviceSerial
	 * @param channelNo
	 * @param expireTime
	 * @return
	 */
	public String getLiveAddress(String deviceSerial,Integer channelNo, Integer expireTime){

		Map<String,String> data = new HashMap<String,String>();
		data.put("accessToken", getAccessToken());
		data.put("deviceSerial", deviceSerial);
		data.put("channelNo", String.valueOf(channelNo));
		if(expireTime!=null){
			data.put("expireTime", String.valueOf(expireTime));
		}
		
		String result = HttpUtil.post(Constants.YS_DEVICE_LIVE_ADDRESS_URL, "UTF-8", data);
		log.info("结果"+result);
		JsonNode node = JsonObject.getJson(result);
		
		String code = node.get("code").asText();

		if ("200".equals(code)) {
			return node.get("data").toString();
		}
		return null;
		
	}
	
	/**
	 * nvr绑定摄像头
	 * @param deviceSerial 设备序列号
	 * @param ipcSerial 待关联的摄像头序列号
	 * @param validateCode 摄像头设备验证码
	 */
	public void addNvrCamare(String deviceSerial,String ipcSerial,String validateCode){
		Map<String,String> data = new HashMap<String,String>();
		data.put("accessToken", getAccessToken());
		data.put("deviceSerial", deviceSerial);
		data.put("ipcSerial", ipcSerial);
		if(!StringUtil.isEmpty(validateCode)){
			data.put("validateCode", validateCode);
		}
		String result = HttpUtil.post(Constants.YS_DEVICE_NVR_CAMERA_ADD, "UTF-8", data);
		try {
			ObjectMapper objectMapper = JsonObject.getObjectMapper();
			JsonNode node = objectMapper.readTree(result);
			
			String code = node.get("code").asText();
			if("200".equals(code)){
				log.info("绑定成功,nvr:"+deviceSerial+",ipcSerial:"+ipcSerial);
				return;
			}
			log.error("绑定失败,nvr:"+deviceSerial+",ipcSerial:"+ipcSerial);
			
		}catch(Exception e){
			log.error("绑定失败:原因:{}",e.getMessage());
		}
		
	}
	
	
	/**
	 * nvr绑定摄像头
	 * @param deviceSerial 设备序列号
	 * @param ipcSerial 待关联的摄像头序列号
	 * @param validateCode 摄像头设备验证码
	 */
	public void delNvrCamare(String deviceSerial,String ipcSerial,String validateCode){
		Map<String,String> data = new HashMap<String,String>();
		data.put("accessToken", getAccessToken());
		data.put("deviceSerial", deviceSerial);
		data.put("ipcSerial", ipcSerial);
		if(!StringUtil.isEmpty(validateCode)){
			data.put("validateCode", validateCode);
		}
		String result = HttpUtil.post(Constants.YS_DEVICE_NVR_CAMERA_DEL, "UTF-8", data);
		try {
			ObjectMapper objectMapper = JsonObject.getObjectMapper();
			JsonNode node = objectMapper.readTree(result);
			
			String code = node.get("code").asText();
			if("200".equals(code)){
				log.info("绑定成功,nvr:"+deviceSerial+",ipcSerial:"+ipcSerial);
				return;
			}
			log.error("绑定失败,nvr:"+deviceSerial+",ipcSerial:"+ipcSerial);
			
		}catch(Exception e){
			log.error("绑定失败:原因:{}",e.getMessage());
		}
		
	}
	
}
