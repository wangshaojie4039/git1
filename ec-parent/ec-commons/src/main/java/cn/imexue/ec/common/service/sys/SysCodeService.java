package cn.imexue.ec.common.service.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.imexue.ec.common.model.SysCode;


/**
 * 系统代码相关的服务
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年2月20日
 * @author lijianfeng
 * @version 1.0
 */
@Service
public class SysCodeService {
	private static final Logger log = LoggerFactory.getLogger(SysCodeService.class);	
	
	public static final String CHILD_REL = "CHILD_REL";//家长与幼儿之间的关系
	public static final String EXPRESS = "EXPRESS";//快递公司
	public static final String RECIPE_TYPE = "RECIPE_TYPE";//食谱类型
	public static final String LEAVE_TYPE = "LEAVE_TYPE";//请假类型
	public static final String USER_ROLE = "USER_ROLE";//用户角色
	public static final String STAFF_ROLE = "STAFF_ROLE";//员工角色
	public static final String APP_TYPE = "APP_TYPE";//app类型
	public static final String APP_PLATFORM = "APP_PLATFORM";//运行平台
	public static final String APP_PRODUCT_TYPE = "APP_PRODUCT_TYPE";//产品类型
	public static final String CUST_PRODUCT_TYPE = "CUST_PRODUCT_TYPE";//产品类型
	
	public static final String CHILD_REL_BB = "BB";//爸爸
	public static final String CHILD_REL_MM = "MM";//妈妈
	public static final String CHILD_REL_YY = "YY";//爷爷
	public static final String CHILD_REL_NN = "NN";//奶奶
	public static final String CHILD_REL_WG = "WG";//外公
	public static final String CHILD_REL_WP = "WP";//外婆
	public static final String CHILD_REL_OTHER = "OTHER";//其他
	
	public static final String EXPRESS_YTO = "YTO";//圆通
	public static final String EXPRESS_ZTO = "ZTO";//中通
	public static final String EXPRESS_STO = "STO";//申通
	public static final String EXPRESS_BEST = "BEST";//百世汇通
	public static final String EXPRESS_YUNDA = "YUNDA";//韵达
	public static final String EXPRESS_SF = "SF";//顺丰
	public static final String EXPRESS_ANE = "ANE";//安能物流
	public static final String EXPRESS_DEPPON = "DEPPON";//德邦物流
	public static final String EXPRESS_EMS = "EMS";//EMS
	public static final String EXPRESS_OTHER = "OTHER";//其它
	
	public static final String RECIPE_B = "B";//早餐
	public static final String RECIPE_BP = "BP";//早加餐
	public static final String RECIPE_D = "D";//午餐
	public static final String RECIPE_DP = "DP";//午加餐
	public static final String RECIPE_D2 = "D2";//晚餐
	public static final String RECIPE_D2P = "D2P";//晚加餐
	
	public static final String LEAVE_TYPE_BL = "BL";//事假
	public static final String LEAVE_TYPE_SL = "SL";//病假
	public static final String LEAVE_TYPE_OTHER = "OTHER";//其他
	
	public static final String USER_ROLE_CUST = "CUST";//客户
	public static final String USER_ROLE_S = "S";//员工
	public static final String USER_ROLE_P = "P";//家长
	public static final String USER_ROLE_T = "T";//老师/园长
	public static final String USER_ROLE_SG = "SG";//总校
	public static final String USER_ROLE_C = "C";//幼儿
	
	public static final String STAFF_ROLE_ADM = "ADM";//管理员
	public static final String STAFF_ROLE_FIN = "FIN";//财务
	public static final String STAFF_ROLE_MM = "MM";//市场负责人
	public static final String STAFF_ROLE_MS = "MS";//市场人员
	public static final String STAFF_ROLE_WM = "WM";//仓库
	public static final String STAFF_ROLE_LEA = "LEA";//领导
	public static final String STAFF_ROLE_CS = "CS";//客服
	
	public static final String APP_TYPE_PARENT = "P";//家长端
	public static final String APP_TYPE_TEACHER = "T";//老师端
	
	public static final String APP_PLATFORM_ANDROID = "android";//Android
	public static final String APP_PLATFORM_IOS = "ios";//IOS
	
	public static final String APP_PRODUCT_TYPE_CAMERA = "CAMERA";//视频包月
	
	public static final String CUST_PRODUCT_TYPE_ATTENDANCE = "ATTENDANCE";//考勤机
	public static final String CUST_PRODUCT_TYPE_CAMERA = "CAMERA";//摄像头
	public static final String CUST_PRODUCT_TYPE_CARD = "CARD";//考勤卡
	
	private static Map<String, String> CHILD_REL_MAP;
	private static Map<String, String> EXPRESS_MAP;
	private static Map<String, String> RECIPE_TYPE_MAP;
	private static Map<String, String> LEAVE_TYPE_MAP;
	private static Map<String, String> USER_ROLE_MAP;
	private static Map<String, String> STAFF_ROLE_MAP;
	private static Map<String, String> APP_TYPE_MAP;
	private static Map<String, String> APP_PLATFORM_MAP;
	private static Map<String, String> APP_PRODUCT_TYPE_MAP;
	private static Map<String, String> CUST_PRODUCT_TYPE_MAP;
	
	public static List<SysCode> CHILD_REL_LIST;
	public static List<SysCode> EXPRESS_LIST;
	public static List<SysCode> RECIPE_TYPE_LIST;
	public static List<SysCode> LEAVE_TYPE_LIST;
	public static List<SysCode> USER_ROLE_LIST;
	public static List<SysCode> STAFF_ROLE_LIST;
	public static List<SysCode> APP_TYPE_LIST;
	public static List<SysCode> APP_PLATFORM_LIST;
	public static List<SysCode> APP_PRODUCT_TYPE_LIST;
	public static List<SysCode> CUST_PRODUCT_TYPE_LIST;
	
	@Resource
	private SysCacheService sysCacheService;
	
	
	@PostConstruct
	public synchronized void init() {
		log.info("ready to init sys code");
		
		CHILD_REL_MAP = new HashMap<String, String>();
		EXPRESS_MAP = new HashMap<String, String>();
		RECIPE_TYPE_MAP = new HashMap<String, String>();
		LEAVE_TYPE_MAP = new HashMap<String, String>();
		USER_ROLE_MAP = new HashMap<String, String>();
		STAFF_ROLE_MAP = new HashMap<String, String>();
		APP_TYPE_MAP  = new HashMap<String, String>();
		APP_PLATFORM_MAP = new HashMap<String, String>();
		APP_PRODUCT_TYPE_MAP = new HashMap<String, String>();
		CUST_PRODUCT_TYPE_MAP = new HashMap<String, String>();
		
		CHILD_REL_LIST = sysCacheService.selectByType(CHILD_REL);
		for (SysCode sysCode : CHILD_REL_LIST) {
			CHILD_REL_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		EXPRESS_LIST = sysCacheService.selectByType(EXPRESS);
		for (SysCode sysCode : EXPRESS_LIST) {
			EXPRESS_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		RECIPE_TYPE_LIST = sysCacheService.selectByType(RECIPE_TYPE);
		for (SysCode sysCode : RECIPE_TYPE_LIST) {
			RECIPE_TYPE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		LEAVE_TYPE_LIST = sysCacheService.selectByType(LEAVE_TYPE);
		for (SysCode sysCode : LEAVE_TYPE_LIST) {
			LEAVE_TYPE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		USER_ROLE_LIST = sysCacheService.selectByType(USER_ROLE);
		for (SysCode sysCode : USER_ROLE_LIST) {
			USER_ROLE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		List<SysCode> STAFF_ROLE_LIST = sysCacheService.selectByType(STAFF_ROLE);
		for (SysCode sysCode : STAFF_ROLE_LIST) {
			STAFF_ROLE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		APP_TYPE_LIST = sysCacheService.selectByType(APP_TYPE);
		for (SysCode sysCode : APP_TYPE_LIST) {
			APP_TYPE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		APP_PLATFORM_LIST = sysCacheService.selectByType(APP_PLATFORM);
		for (SysCode sysCode : APP_PLATFORM_LIST) {
			APP_PLATFORM_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		APP_PRODUCT_TYPE_LIST = sysCacheService.selectByType(APP_PRODUCT_TYPE);
		for (SysCode sysCode : APP_PRODUCT_TYPE_LIST) {
			APP_PRODUCT_TYPE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		CUST_PRODUCT_TYPE_LIST = sysCacheService.selectByType(APP_PRODUCT_TYPE);
		for (SysCode sysCode : CUST_PRODUCT_TYPE_LIST) {
			CUST_PRODUCT_TYPE_MAP.put(sysCode.getCode(), sysCode.getValue());
		}
		
		log.info("init sys code complete.");
	}

	public static String getByTypeAndCode(String type, String code) {
		if (CHILD_REL.equals(type)) {
			return CHILD_REL_MAP.get(code);
		} else if (EXPRESS.equals(type)) {
			return EXPRESS_MAP.get(code);
		} else if (RECIPE_TYPE.equals(type)) {
			return RECIPE_TYPE_MAP.get(code);
		} else if (LEAVE_TYPE.equals(type)) {
			return LEAVE_TYPE_MAP.get(code);
		} else if (USER_ROLE.equals(type)) {
			return USER_ROLE_MAP.get(code);
		} else if (STAFF_ROLE.equals(type)) {
			return STAFF_ROLE_MAP.get(code);
		} else if (APP_PLATFORM.equals(type)) {
			return APP_PLATFORM_MAP.get(code);
		} else if (APP_PRODUCT_TYPE.equals(type)) {
			return APP_PRODUCT_TYPE_MAP.get(code);
		} else if (CUST_PRODUCT_TYPE.equals(type)) {
			return CUST_PRODUCT_TYPE_MAP.get(code);
		} else {
			return null;
		}
	}
	
	public static List<SysCode> getChildRelList() {
		return CHILD_REL_LIST;
	}
	
	public static List<SysCode> getRecipeTypeList() {
		return RECIPE_TYPE_LIST;
	}
	
	public static List<SysCode> getLeaveTypeList() {
		return LEAVE_TYPE_LIST;
	}
	
	public static List<SysCode> getUserRoleList() {
		return USER_ROLE_LIST;
	}
	
	public static List<SysCode> getStaffRoleList() {
		return STAFF_ROLE_LIST;
	}
	
	public static List<SysCode> getAppTypeList() {
		return APP_TYPE_LIST;
	}
	
	public static List<SysCode> getAppPlatformList() {
		return APP_PLATFORM_LIST;
	}
	
	public static List<SysCode> getAppProductTypeList() {
		return APP_PRODUCT_TYPE_LIST;
	}
	
	public static List<SysCode> getCustProductTypeList() {
		return CUST_PRODUCT_TYPE_LIST;
	}	
}
