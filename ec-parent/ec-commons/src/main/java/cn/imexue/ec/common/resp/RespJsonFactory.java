package cn.imexue.ec.common.resp;

import java.util.Locale;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * RespJson工厂类
 * @author hl
 *
 */
public class RespJsonFactory implements ApplicationListener<ContextRefreshedEvent> {

	private static MessageSource messageSource;
	
	private static String successStr = "成功";
	
	private static String failStr = "失败";
	
	public static final Integer SUCCESS = 1;
	
	public static final Integer FAIL = 0;
	
	public static RespJson buildSuccess(){
		return buildSuccess(null);
	}
	
	public static RespJson buildSuccess(Object data){
		RespJson respJson = new RespJson();
		respJson.setResult(SUCCESS);
		respJson.setMsg(successStr);
		respJson.setData(data);
		return respJson;
	}
	
	public static RespJson buildFailure(){
		return buildFailure(failStr);
	}
	
	public static RespJson buildFailure(String msg){
		return buildFailure(null, msg);
	}
	
	public static RespJson buildFailure(String msg,Integer code){
		RespJson respJson = buildFailure(null, msg);
		respJson.setCode(code);
		return respJson;
	}
	
	public static RespJson buildFailure(Object data,String msg){
		RespJson respJson = new RespJson();
		respJson.setResult(FAIL);
		respJson.setMsg(msg);
		respJson.setData(data);
		return respJson;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		messageSource = event.getApplicationContext().getBean(MessageSource.class);
		successStr = messageSource.getMessage("app.success", null, Locale.getDefault());
		failStr = messageSource.getMessage("app.fail", null, Locale.getDefault());
		
	}
}
