package cn.imexue.ec.common.util.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.MDC;

/**
 * 登陆信息控制类，把登陆信息放到线程中
 * @author hl
 *
 */
public class LoginUtil {

	private static final String SESSION_LOGININFO = "logininfo";
	
	private static final ThreadLocal<LoginInfo> loginInfo = new ThreadLocal<>();
	
	/**
	 * 
	 * 把登陆信息放到session里，登陆时调用
	 * @param session
	 * @param userId 用户id
	 * @param loginId 用户登陆id
	 * @param name 用户姓名
	 * @param role 用户角色
	 * @param divisions 用户代理区域
	 */
	public static void setLoginInfoToSession(HttpSession session ,String userRole, Long userId, String loginId, String name,
			String role,Long schoolId){
		LoginInfo login = new LoginInfo(userRole, userId, loginId, name, role, schoolId);
		session.setAttribute(LoginUtil.SESSION_LOGININFO, login);
	}
	
	/**
	 * 把session放到线程中,过滤器调用
	 * @param session
	 */
	public static void setLoginFromRequest(HttpServletRequest request){
		Object attribute = request.getSession().getAttribute(LoginUtil.SESSION_LOGININFO);
		if(attribute == null )
			return ;
		LoginInfo info = (LoginInfo)attribute;
		String loginId = info.getLoginId();
		MDC.put("user", loginId);
		int random = RandomUtils.nextInt(10000, 99999);
		MDC.put("rand", String.valueOf(random));
		loginInfo.set(info);
	}
	
	/**
	 * 获取登陆信息
	 * @return
	 */
	public static LoginInfo getLoginInfo(){
		return loginInfo.get();
	}
	
	/**
	 * 获取登陆人id
	 * @return
	 */
	public static Long getLoginId(){
		LoginInfo info = loginInfo.get();
		if(info == null)
			return null;
		return info.getUserId();
	}
	
	/**
	 * 从线程中移除，过滤器调用
	 */
	public static void removeLoginInfo(){
		MDC.remove("user");
		MDC.remove("rand");
		loginInfo.set(null);
	}
	
	/**
	 * @return 学校id
	 */
	public static Long getSchoolId(){
		LoginInfo info = loginInfo.get();
		return info.getSchoolId();
	}
	
}
