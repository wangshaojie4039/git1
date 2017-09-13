package cn.imexue.ec.common.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.imexue.ec.common.model.bo.sms.SmsMessageBO;

/**
 * 短信任务管理器
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月15日
 * @author lijianfeng
 * @version 1.0
 */
public class SMSTaskManager {
	private static int corePoolSize = 100;

	private static int maximumPoolSize = 50000;

	private static int keepAliveTime = 0;

	private static TimeUnit unit = TimeUnit.MILLISECONDS;

	private static int queueSize = 1000;
	
	public static ThreadPoolExecutor THREAD_POOL;
	
	/**短信记录队列，用于批量提交事务*/
	public static Map<String, List<SmsMessageBO>> SMS_MESSAGE_QUEUE;

	static {
		THREAD_POOL = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, unit, new ArrayBlockingQueue<Runnable>(queueSize));
		
		SMS_MESSAGE_QUEUE = new ConcurrentHashMap<>();
	}
	
	public static void putToQueue(String key, SmsMessageBO bo) {
		List<SmsMessageBO> queueList = SMS_MESSAGE_QUEUE.get(key);
		if (queueList == null) {
			queueList = new ArrayList<SmsMessageBO>();
		}
		queueList.add(bo);
		SMS_MESSAGE_QUEUE.put(key, queueList);
	}
	
	public static void removeFromQueue(String key) {
		SMS_MESSAGE_QUEUE.remove(key);
	}
}
