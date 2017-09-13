package cn.imexue.ec.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.imexue.ec.common.model.common.UserRoleCode;

public class RoleService {
	private static Map<String, String> ROLE_MAP;
	private static List<UserRoleCode> ROLE_LIST;
	
	private static Map<String, String> STAFF_ROLE_MAP;
	private static List<UserRoleCode> STAFF_ROLE_LIST;
	
	public static final String ROLE_CUSTOMER = "CUST";//客户（代理商/订货商）
	public static final String ROLE_STAFF = "S";//员工
	public static final String ROLE_PARENT = "P";//家长
	public static final String ROLE_TEACHER = "T";//老师
	public static final String ROLE_SCHOOL_GROUP = "SG";//总校
	
	public static final String STAFF_ROLE_ADMIN = "ADM";//管理员
	public static final String STAFF_ROLE_FINANCE = "FIN";//财务
	public static final String STAFF_ROLE_WAREHOUSE_MANAGER = "WM";//仓库
	public static final String STAFF_ROLE_MARKET_MANAGER = "MM";//市场负责人
	public static final String STAFF_ROLE_MARKET_STAFF = "MS";//市场人员
	public static final String STAFF_ROLE_LEADER = "LEA";//领导
	public static final String STAFF_ROLE_CUSTOMER_SERVICE = "CS";//客服，暂不需要
	
	public static final Byte CUSTOMER_ROLE_TYPE_AGENCY = 1;//代理商
	public static final Byte CUSTOMER_ROLE_TYPE_INDENTOR = 2;//订货商

	static {
		ROLE_MAP = new HashMap<String, String>();
		ROLE_LIST = new ArrayList<UserRoleCode>();
		
		ROLE_LIST.add(new UserRoleCode(ROLE_CUSTOMER, "代理商/订货商"));
		ROLE_LIST.add(new UserRoleCode(ROLE_STAFF, "员工"));
		ROLE_LIST.add(new UserRoleCode(ROLE_PARENT, "家长"));
		ROLE_LIST.add(new UserRoleCode(ROLE_TEACHER, "教师"));
		ROLE_LIST.add(new UserRoleCode(ROLE_SCHOOL_GROUP, "总校"));
		
		for (UserRoleCode userRole : ROLE_LIST) {
			ROLE_MAP.put(userRole.getCode(), userRole.getName());
		}
		
		
		STAFF_ROLE_MAP = new HashMap<String, String>();
		STAFF_ROLE_LIST = new ArrayList<UserRoleCode>();
		
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_ADMIN, "系统管理员"));
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_FINANCE, "财务"));
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_MARKET_MANAGER, "市场负责人"));
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_MARKET_STAFF, "市场人员"));
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_WAREHOUSE_MANAGER, "仓库"));
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_LEADER, "领导"));
		STAFF_ROLE_LIST.add(new UserRoleCode(STAFF_ROLE_CUSTOMER_SERVICE, "客服"));
		
		for (UserRoleCode userRole : STAFF_ROLE_LIST) {
			STAFF_ROLE_MAP.put(userRole.getCode(), userRole.getName());
		}
	}
	
	public static Map<String, String> getRoleMap(){
		return ROLE_MAP;
	}
	
	public static List<UserRoleCode> getRoleList(){
		return ROLE_LIST;
	}
	
	public static Map<String, String> getStaffRoleMap(){
		return STAFF_ROLE_MAP;
	}
	
	public static List<UserRoleCode> getStaffRoleList(){
		return STAFF_ROLE_LIST;
	}
	
	public static boolean isCustomerRole(String role) {
		return ROLE_CUSTOMER.equals(role);
	}
	
	public static boolean isStaffRole(String role) {
		return ROLE_STAFF.equals(role);
	}
	
	public static String getStaffRoleNameByRole(String role) {
		return STAFF_ROLE_MAP.get(role);
		
	}
}
