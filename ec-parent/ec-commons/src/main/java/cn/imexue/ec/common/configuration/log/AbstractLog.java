package cn.imexue.ec.common.configuration.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import cn.imexue.ec.common.util.JsonObject;

public abstract class AbstractLog {

	protected abstract void errorExecute(Throwable e);
	
	protected Object log(ProceedingJoinPoint pjd,Logger log)throws Throwable  {
		String methodName = pjd.toShortString();
		List<Object> asList = Arrays.asList(pjd.getArgs());
		List<Object> args = new ArrayList<>(asList);
		
		long now = System.currentTimeMillis();
		Object ret = null;
		String json = JsonObject.beanToJson(args);
		String argsStr = json==null?args.toString():json;
		try{
			if(args.size()>0){
				ret = pjd.proceed(pjd.getArgs());
			}else{
				ret = pjd.proceed();
			}
		}catch(Throwable e){
			long cost = System.currentTimeMillis()-now;
			log.error("执行{}出错,参数为{},错误信息:{},耗时{}毫秒", methodName,argsStr,e.getMessage(),cost);
			errorExecute(e);
			//			log.error("接口出错,信息:{}",e.getMessage());
			throw e;
		}
		long cost = System.currentTimeMillis()-now;
		if(ret==null){
			log.info("执行{}完成，入参为{},返回{},执行耗时{}毫秒", methodName,argsStr,ret,cost);
			return null;
		}
		String retString = JsonObject.beanToJson(ret);
		log.info("执行{}完成，入参为{},返回{},执行耗时{}毫秒", methodName,argsStr,retString,cost);
		
		return ret;
	}
	
}