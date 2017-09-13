package cn.imexue.ec.common.service.sys;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.imexue.ec.common.mapper.ec.SmsRecordMapper;
import cn.imexue.ec.common.model.SmsRecord;
import cn.imexue.ec.common.model.bo.sms.SmsMessageBO;
//import cn.imexue.ec.common.model.bo.sms.SmsMessageBO;
import cn.imexue.ec.common.model.bo.sms.SmsRequestBO;
import cn.imexue.ec.common.model.bo.sms.SmsRequestMBO;
import cn.imexue.ec.common.model.bo.sms.SmsTelBO;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.HttpClientUtil;
import cn.imexue.ec.common.util.JsonObject;

import com.fasterxml.jackson.databind.JsonNode;
@Component
public class SMSTencentService {
	private static final Logger logger = LoggerFactory.getLogger(SMSTencentService.class);
	
	@Resource
	private SmsRecordMapper smsRecordMapper;
	
	@Value("${app.sms.key}")
	private String smsKey;
	
	@Value("${app.sms.url}")
	private String smsUrl;
	
	@Value("${app.multisms2.url}")
	private String multisms2Url;
	
	@Value("${app.voicesms.url}")
	private String voicesmsUrl;
	
	private static boolean sendSms = true;
	
	/**
	 * 短信发送接口
	 * @param destmobile 手机号码，多个以分号分割
	 * @param message 短信内容，只传输验证码内容
	 * @param templateType 短信模板，参照cn.imexue.ec.common.util.Constants.java
	 * @return
	 */
	public void sendMessage(String destmobile, String message,int templateType) {
		if(!sendSms) return;
		String[] tmpPhone = destmobile.split(";");
		if (tmpPhone.length > 0) {
			if (tmpPhone.length == 1) {
				SmsRequestBO requestBean = new SmsRequestBO();
				SmsTelBO telBean = new SmsTelBO("86", destmobile);
				requestBean.setTel(telBean);
				requestBean.setType("0");
				requestBean.setMsg(message);
				try {
					requestBean.setSig(DigestUtils
							.md5Hex((smsKey + destmobile).getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					logger.error("sign 设置失败：" + e.getMessage());
					return ;
				}
				String response= HttpClientUtil.doPost(smsUrl + getRandomChar(6),
						JsonObject.beanToJson(requestBean));
				//记录到t_sms_record表中
				if (response != null) {
					Map<String, Object> json = JsonObject.jsonToMap(response);
					String result = (String) json.get("result");
					String sid = (String) json.get("sid");
					String fee = json.get("fee")!=null?json.get("fee").toString():null ;
					SmsRecord record = new SmsRecord();
					record.setTemplateType(templateType);
					record.setMobile(destmobile);
					record.setMsg(message);
					record.setSmsType(Byte.valueOf("1"));
					record.setResult(Integer.valueOf(result));
					record.setSid(sid);
					record.setFee(fee!= null?Integer.valueOf(fee):null);
					record.setCreateTime(DateUtil.getCurrentTimestamp());
					record.setCreatorRole(Constants.SYS_ROLE);
					record.setCreatorId(Constants.SYS_ROLE_ID);
					smsRecordMapper.insert(record);
				}

			} else {
				SmsRequestMBO requestBean = new SmsRequestMBO();
				List<SmsTelBO> tels = new ArrayList<SmsTelBO>();
				for (String tel : tmpPhone) {
					tels.add(new SmsTelBO("86", tel));
				}
				requestBean.setTel(tels);
				requestBean.setType("0");
				requestBean.setMsg(message);
				try {
					requestBean.setSig(DigestUtils.md5Hex((smsKey + destmobile
							.replace(";", ",")).getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					logger.error("sign 设置失败：" + e.getMessage());
					return ;
				}
				String response= HttpClientUtil.doPost(multisms2Url + getRandomChar(6),
						JsonObject.beanToJson(requestBean));
				// 记录到t_sms_record表中
				if (response != null) {
					Date createTime = DateUtil.getCurrentTimestamp();
					for (String tel : tmpPhone) {
						Map<String, Object> map = JsonObject.jsonToMap(response);
						String result = (String) map.get("result");
						String sid = (String) map.get("sid");
						String fee = (String) map.get("fee");
						SmsRecord record = new SmsRecord();
						record.setTemplateType(templateType);
						record.setMobile(tel);
						record.setMsg(message);
						record.setSmsType(Byte.valueOf("1"));
						record.setResult(Integer.valueOf(result));
						record.setSid(sid);
						record.setFee(Integer.valueOf(fee));
						record.setCreateTime(createTime);
						record.setCreatorRole(Constants.SYS_ROLE);
						record.setCreatorId(Constants.SYS_ROLE_ID);
						smsRecordMapper.insert(record);
					}
				}
			}
		}
	}
	
	/**
	 * 异步发送短信，不返回发送结果，发送成功后将发送记录
	 * @param mobile 手机号码，多个以分号分割
	 * @param message 短信内容，只传输验证码内容
	 */
	public void sendMessageAsync(String mobile,String message,int templateType) {
		if(!sendSms) return;
		SMSTaskManager.THREAD_POOL.execute(new SMSTask(mobile, message,templateType));
	}
	

	/**
	 * 从队列中统一发送短信，防止一个事务中需要发送多条短信的情况
	 *@param 队列中smsid
	 */
	public void sendMessageFromQueue(String key) {
		if(!sendSms) return;
		List<SmsMessageBO> queueList = SMSTaskManager.SMS_MESSAGE_QUEUE.get(key);
		if (queueList != null) {
			for (SmsMessageBO bo : queueList) {
				SMSTaskManager.THREAD_POOL.execute(new SMSTask(bo.getMobile(),bo.getMsg(),bo.getTemplateType()));
			}
			SMSTaskManager.removeFromQueue(key);
		}
	}
	
	private class SMSTask implements Runnable{
		private Logger log = LoggerFactory.getLogger(SMSTask.class);
		
		private String mobile;
		private String message;
		private int templateType;

		public SMSTask(String mobile, String message, int templateType) {
			super();
			this.mobile = mobile;
			this.message = message;
			this.templateType = templateType;
		}

		public void run(){
			try {
				String[] tmpPhone = mobile.split(";");
				if (tmpPhone.length > 0) {
					if (tmpPhone.length == 1) {
						SmsRequestBO requestBean = new SmsRequestBO();
						SmsTelBO telBean = new SmsTelBO("86", mobile);
						requestBean.setTel(telBean);
						requestBean.setType("0");
						requestBean.setMsg(message);
						requestBean.setSig(DigestUtils.md5Hex((smsKey + mobile).getBytes("UTF-8")));

						String response = HttpClientUtil.doPost(smsUrl + getRandomChar(6),
								JsonObject.beanToJson(requestBean));
						
						//记录到t_sms_record表中
						if (response != null) {
							JsonNode json = JsonObject.getJson(response);
							String result = json.get("result").asText();
							String sid = json.get("sid").asText();
							String fee = json.get("fee").asText();
							SmsRecord record = new SmsRecord();
							record.setTemplateType(templateType);
							record.setMobile(mobile);
							record.setMsg(message);
							record.setSmsType(Byte.valueOf("1"));
							record.setResult(Integer.valueOf(result));
							record.setSid(sid);
							record.setFee(Integer.valueOf(fee));
							record.setCreateTime(DateUtil.getCurrentTimestamp());
							record.setCreatorRole(Constants.SYS_ROLE);
							record.setCreatorId(Constants.SYS_ROLE_ID);
							smsRecordMapper.insert(record);
						}
	
					} else {
						SmsRequestMBO requestBean = new SmsRequestMBO();
						List<SmsTelBO> tels = new ArrayList<SmsTelBO>();
						for (String tel : tmpPhone) {
							tels.add(new SmsTelBO("86", tel));
						}
						requestBean.setTel(tels);
						requestBean.setType("0");
						requestBean.setMsg(message);
						requestBean.setSig(DigestUtils.md5Hex((smsKey + mobile.replace(";", ",")).getBytes("UTF-8")));
						String response = HttpClientUtil.doPost(multisms2Url + getRandomChar(6),
								JsonObject.beanToJson(requestBean));
						
						// 记录到t_sms_record表中
						if (response != null) {
							Date createTime = DateUtil.getCurrentTimestamp();
							for (String tel : tmpPhone) {
								JsonNode json = JsonObject.getJson(response);
								String result = json.get("result").asText();
								String sid = json.get("sid").asText();
								String fee = json.get("fee").asText();
								SmsRecord record = new SmsRecord();
								record.setTemplateType(templateType);
								record.setMobile(tel);
								record.setMsg(message);
								record.setSmsType(Byte.valueOf("1"));
								record.setResult(Integer.valueOf(result));
								record.setSid(sid);
								record.setFee(Integer.valueOf(fee));
								record.setCreateTime(createTime);
								record.setCreatorRole(Constants.SYS_ROLE);
								record.setCreatorId(Constants.SYS_ROLE_ID);
								smsRecordMapper.insert(record);
							}
						}
					}
				}
			} catch (Throwable e) {
				log.error(e.getMessage(), e);
			}
		}
		
	}

	/**
	 * 语音验证码
	 * @param destmobile 手机号码，只能一个
	 * @param message 短信内容，只传输验证码内容
	 * @return
	 */
	public void sendAudio(String destmobile, String message) {
		SmsRequestBO requestBean = new SmsRequestBO();
		SmsTelBO telBean = new SmsTelBO("86", destmobile);
		requestBean.setTel(telBean);
		requestBean.setType("0");
		requestBean.setMsg(message);
		try {
			requestBean.setSig(DigestUtils.md5Hex((smsKey + destmobile)
					.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error("sign 设置失败：" + e.getMessage());
			return ;
		}

		String response= HttpClientUtil.doPost(voicesmsUrl + getRandomChar(6),
				JsonObject.beanToJson(requestBean));
		//记录到t_sms_record表中
		if (response != null) {
			JsonNode json = JsonObject.getJson(response);
			String result = json.get("result").asText();
			SmsRecord record = new SmsRecord();
			record.setTemplateType(0);
			record.setMobile(destmobile);
			record.setMsg(message);
			record.setSmsType(Byte.valueOf("1"));
			record.setResult(Integer.valueOf(result));
			record.setCreateTime(DateUtil.getCurrentTimestamp());
			record.setCreatorRole(Constants.SYS_ROLE);
			record.setCreatorId(Constants.SYS_ROLE_ID);
			smsRecordMapper.insert(record);
		}

	}

	public boolean isSendSms() {
		return sendSms;
	}

	public void setSendSms(boolean sendSms) {
		SMSTencentService.sendSms = sendSms;
	}

	private String getRandomChar(int size) {
		String result = "";
		for (int i = 0; i < size; i++) {
			int intVal = (int) (Math.random() * 26 + 97);
			result = result + (char) intVal;
		}
		return result;
	}
}
