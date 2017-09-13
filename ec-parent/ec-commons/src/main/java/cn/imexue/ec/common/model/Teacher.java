package cn.imexue.ec.common.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.imexue.ec.common.model.common.DataEntity;

public class Teacher extends DataEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7749186296286041958L;

	private String mobile;
	
	private String uid;
	
	private String uuid;
	
	@JsonIgnore
	private String password;
	
	private String name;
	
	private Byte sex;
	
	private Date birthday;
	
	private Byte hasLogin;
	
	private Byte isActive;

	private String logoUrl;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Byte getHasLogin() {
		return hasLogin;
	}

	public void setHasLogin(Byte hasLogin) {
		this.hasLogin = hasLogin;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}