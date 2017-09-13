package cn.imexue.ec.common.util.login;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class LoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7526374154765450172L;

	
	
	public LoginInfo(String userRole, Long userId, String loginId, String name,
			String role,Long schoolId) {
		this.userRole = userRole;
		this.userId = userId;
		this.loginId = loginId;
		this.name = name;
		this.role = role;
		this.schoolId = schoolId;
	}

	private String userRole;
	
	private Long userId;
	
	private String loginId;
	
	private String name;
	
	private String role;
	
	private Long schoolId;
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	
}
